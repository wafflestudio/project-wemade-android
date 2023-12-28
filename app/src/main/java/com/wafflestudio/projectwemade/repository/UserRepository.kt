package com.wafflestudio.projectwemade.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wafflestudio.projectwemade.model.dto.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepository @Inject constructor() {

    private var userReference =
        Firebase.database("https://wemade-project-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference.child("users")

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun signUp(
        username: String,
        password: String,
        onUsernameDuplicated: () -> Unit,
        onSuccess: () -> Unit
    ) {
        userReference.orderByChild("username").equalTo(username).get().addOnSuccessListener {
            if (it.exists()) {
                onUsernameDuplicated()
                return@addOnSuccessListener
            } else {
                val key = userReference.push().key!!
                userReference.child(key).apply {
                    child("uid").setValue(key)
                    child("username").setValue(username)
                    child("password").setValue(password)
                }
                onSuccess()
            }
        }
    }

    fun signIn(
        username: String,
        password: String,
        onUserNotFound: () -> Unit,
        onPasswordMismatch: () -> Unit,
        onSuccess: () -> Unit,
    ) {
        userReference.orderByChild("username").equalTo(username).get().addOnSuccessListener {
            if (it.exists()) {
                it.children.first().let { user ->
                    if (user.child("password").getValue(String::class.java) == password) {
                        _user.value = User(
                            uid = user.child("uid").getValue(String::class.java) ?: "",
                            username = user.child("username").getValue(String::class.java) ?: ""
                        )
                        onSuccess()
                    } else {
                        onPasswordMismatch()
                        return@addOnSuccessListener
                    }
                }
            } else {
                onUserNotFound()
                return@addOnSuccessListener
            }
        }
    }
}