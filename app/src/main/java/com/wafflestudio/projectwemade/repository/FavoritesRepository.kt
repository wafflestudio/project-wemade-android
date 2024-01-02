package com.wafflestudio.projectwemade.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.util.readMenu
import com.wafflestudio.projectwemade.util.writeMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(
    private val userRepository: UserRepository,
    private val externalScope: CoroutineScope,
) {
    private val _favorites = MutableStateFlow<List<Menu>>(emptyList())
    val favorites: StateFlow<List<Menu>> get() = _favorites


    private val favoriteListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            _favorites.value = snapshot.child("favorites").children.map {
                it.readMenu()
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    init {
        externalScope.launch {
            userRepository.user.collect {
                userRepository.withUserSnapshot(
                    onSignedOut = { snapshot ->
                        snapshot.ref.removeEventListener(favoriteListener)
                        _favorites.value = emptyList()
                    }
                ) { snapshot ->
                    snapshot.ref.addValueEventListener(favoriteListener)
                }
            }
        }
    }

    suspend fun addToFavorites(menu: Menu) {
        userRepository.withUserSnapshot { userSnapshot ->
            userSnapshot.child("favorites").child(menu.id.toString()).writeMenu(menu)
        }
    }

    suspend fun removeFromFavorites(menuId: Int) {
        userRepository.withUserSnapshot { userSnapshot ->
            userSnapshot.child("favorites").child(menuId.toString()).ref.removeValue()
        }
    }
}