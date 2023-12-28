package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Strength
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import com.wafflestudio.projectwemade.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _editingMenu = MutableStateFlow(Menu.Default)
    val editingMenu: StateFlow<Menu> get() = _editingMenu

    private val _isInFavorites = MutableStateFlow(false)
    val isInFavorites: StateFlow<Boolean> get() = _isInFavorites

    suspend fun initializeMenu(
        menuId: Int,
        onMenuNotFound: () -> Unit
    ) {
        _editingMenu.value = menuItemsRepository.getMenu(
            menuId = menuId,
            onMenuNotFound = onMenuNotFound
        )
    }

    fun setTemperature(temperature: Temperature) {
        _editingMenu.value = _editingMenu.value.copy(temperature = temperature)
    }

    fun setStrength(strength: Strength) {
        _editingMenu.value = _editingMenu.value.copy(strength = strength)
    }

    suspend fun addToFavorites() {
        userRepository.addToFavorites(_editingMenu.value)
    }

    suspend fun removeFromFavorites() {
        userRepository.removeFromFavorites(_editingMenu.value.uid)
    }
}