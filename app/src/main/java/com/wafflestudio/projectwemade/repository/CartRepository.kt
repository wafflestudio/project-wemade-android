package com.wafflestudio.projectwemade.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.wafflestudio.projectwemade.model.dto.CartedMenu
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.util.readCartedMenu
import com.wafflestudio.projectwemade.util.writeCartedMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val userRepository: UserRepository,
    private val externalScope: CoroutineScope
) {
    private val _cart = MutableStateFlow<List<CartedMenu>>(emptyList())
    val cart: StateFlow<List<CartedMenu>> get() = _cart

    private val cartListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            _cart.value = snapshot.child("cart").children.map {
                it.readCartedMenu()
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
                        snapshot.ref.removeEventListener(cartListener)
                        _cart.value = emptyList()
                    }
                ) { snapshot ->
                    snapshot.ref.addValueEventListener(cartListener)
                }
            }
        }
    }

    suspend fun addToCart(menu: Menu) {
        userRepository.withUserSnapshot { userSnapshot ->
            val key = userSnapshot.child("cart").ref.push().key
            userSnapshot.child("cart").child(key!!).writeCartedMenu(CartedMenu(key, menu, 1))
        }
    }

    suspend fun updateCartQuantity(cartedMenu: CartedMenu) {
        userRepository.withUserSnapshot { userSnapshot ->
            userSnapshot.child("cart").child(cartedMenu.uid).writeCartedMenu(cartedMenu)
        }
    }

    suspend fun removeFromCart(uid: String) {
        userRepository.withUserSnapshot { userSnapshot ->
            userSnapshot.child("cart").child(uid).ref.removeValue()
        }
    }
}