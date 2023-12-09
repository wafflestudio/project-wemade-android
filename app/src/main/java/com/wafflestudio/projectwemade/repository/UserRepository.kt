package com.wafflestudio.projectwemade.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
    private var auth: FirebaseAuth = Firebase.auth

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    fun signUp(username: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        userReference.orderByChild("user_id").equalTo(username).get().addOnSuccessListener {
            Log.d("asdf", "success")
        }.addOnCanceledListener {
            Log.d("asdf", "cancel")
        }.addOnFailureListener {
            Log.d("asdf", "failure: ${it.message.toString()}")
        }
    }

    fun signIn(username: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
    }
}