package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Strength
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.repository.FavoritesRepository
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import com.wafflestudio.projectwemade.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _editingMenu = MutableStateFlow(Menu.Default)
    val editingMenu: StateFlow<Menu> get() = _editingMenu

    private val favorites get() = favoritesRepository.favorites
    private val _isInFavorites = combine(favorites, _editingMenu) { favoritesList, menu ->
        favoritesList.any { it.id == menu.id }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)
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
        favoritesRepository.addToFavorites(_editingMenu.value)
    }

    suspend fun removeFromFavorites() {
        favoritesRepository.removeFromFavorites(_editingMenu.value.id)
    }
}