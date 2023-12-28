package com.wafflestudio.projectwemade.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Strength
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.model.dto.User
import com.wafflestudio.projectwemade.model.dto.toCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    private var userReference =
        Firebase.database("https://wemade-project-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference.child("users")

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    private val _favorites = MutableStateFlow<List<Menu>>(emptyList())
    val favorites: StateFlow<List<Menu>> get() = _favorites

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
                            username = user.child("username").getValue(String::class.java) ?: "",
                        )
                        user.ref.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                _favorites.value = parseFavorites(snapshot)
                            }

                            override fun onCancelled(error: DatabaseError) {

                            }
                        })
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

    fun signOut() {
        _user.value = null
    }

    suspend fun addToFavorites(
        menu: Menu
    ) {
        _user.value?.let { user ->
            userReference.orderByChild("username").equalTo(user.username).get().await().let {
                if (it.exists()) {
                    it.children.first().let { userSnapshot ->
                        val key = userSnapshot.ref.child("favorites").push().key!!
                        userSnapshot.ref.child("favorites").child(key).apply {
                            child("id").setValue(menu.id)
                            child("uid").setValue(key)
                            child("name").setValue(menu.name)
                            child("category").setValue(menu.category.toString())
                            child("image").setValue(menu.image)
                            child("options").child("temperature").setValue(
                                menu.availableTemperature.map { it.ordinal + 1 }
                            )
                            child("options").child("strength").setValue(
                                menu.availableStrength.map { it.ordinal + 1 }
                            )
                            child("selected_options").child("temperature").setValue(
                                (menu.temperature?.ordinal ?: 0) + 1
                            )
                            child("selected_options").child("strength").setValue(
                                (menu.strength?.ordinal ?: 0) + 1
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun removeFromFavorites(menuUid: String) {
        _user.value?.let { user ->
            userReference.orderByChild("username").equalTo(user.username).get().await().let {
                if (it.exists()) {
                    it.children.first().child("favorites").child(menuUid).ref.removeValue()
                }
            }
        }
    }

    private fun parseFavorites(userSnapshot: DataSnapshot): List<Menu> {
        return userSnapshot.child("favorites").children.map { menu ->
            val availableTemperature = menu.child("options")
                .child("temperature").children.map { t ->
                    Temperature.values()[t.getValue(Int::class.java)?.minus(1) ?: 0]
                }
            val availableStrength = menu.child("options")
                .child("strength").children.map { s ->
                    Strength.values()[s.getValue(Int::class.java)?.minus(1) ?: 0]
                }
            val selectedTemperature = Temperature.values()[menu.child("selected_options")
                .child("temperature").getValue(Int::class.java)?.minus(1) ?: 0]
            val selectedStrength = Strength.values()[menu.child("selected_options")
                .child("strength").getValue(Int::class.java)?.minus(1) ?: 0]

            Menu(
                id = menu.child("id").getValue(Int::class.java) ?: 0,
                uid = menu.child("uid").getValue(String::class.java) ?: "",
                category = menu.child("category").getValue(String::class.java).orEmpty()
                    .toCategory(),
                name = menu.child("name").getValue(String::class.java) ?: "",
                availableTemperature = availableTemperature,
                temperature = selectedTemperature,
                availableStrength = availableStrength,
                strength = selectedStrength,
                image = menu.child("image").getValue(String::class.java)
                    ?: "https://picsum.photos/200",
            )
        }
    }
}