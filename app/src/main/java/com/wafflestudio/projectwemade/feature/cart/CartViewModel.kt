package com.wafflestudio.projectwemade.feature.cart

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.CartedMenu
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartMenus: StateFlow<List<CartedMenu>> get() = cartRepository.cart

    private val _checkedCartMenus = MutableStateFlow<Set<String>>(emptySet())
    val checkedCartMenus: StateFlow<Set<String>> get() = _checkedCartMenus

    suspend fun addToCart(menu: Menu) {
        cartMenus.value.find { it.menu == menu }?.let {
            cartRepository.updateCartQuantity(it.copy(quantity = it.quantity + 1))
        } ?: cartRepository.addToCart(menu)
    }

    suspend fun updateQuantity(cartedMenu: CartedMenu) {
        cartRepository.updateCartQuantity(cartedMenu)
    }

    suspend fun removeFromCart(uid: String) {
        cartRepository.removeFromCart(uid)
        _checkedCartMenus.value = _checkedCartMenus.value.toMutableSet().apply { remove(uid) }
    }

    fun toggleCheckCartMenu(cartedMenu: CartedMenu) {
        _checkedCartMenus.value.let {
            _checkedCartMenus.value = if (it.contains(cartedMenu.uid)) {
                it.toMutableSet().apply { remove(cartedMenu.uid) }
            } else {
                it.toMutableSet().apply { add(cartedMenu.uid) }
            }
        }
    }

    fun toggleCheckAll() {
        if (cartMenus.value.isNotEmpty() && _checkedCartMenus.value.size == cartMenus.value.size) {     //full
            _checkedCartMenus.value = emptySet()
        } else {
            _checkedCartMenus.value = cartMenus.value.map { it.uid }.toSet()
        }
    }
}