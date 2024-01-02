package com.wafflestudio.projectwemade.feature.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.projectwemade.model.dto.Category
import com.wafflestudio.projectwemade.model.dto.Menu
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
class OrderViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val menus get() = menuItemsRepository.menus

    private val _selectedCategory = MutableStateFlow(Category.COFFEE)
    val selectedCategory: StateFlow<Category> get() = _selectedCategory

    val categoryMenus = combine(_selectedCategory, menus) { category, menus ->
        menus.filter { it.category == category }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val favorites: StateFlow<List<Menu>> get() = favoritesRepository.favorites

    private val _favoriteTabState = MutableStateFlow<FavoriteTabState>(FavoriteTabState.Viewing())
    val favoriteTabState get() = _favoriteTabState

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun toggleFavorite(menu: Menu) {
        _favoriteTabState.value.let {
            if (it is FavoriteTabState.Viewing) {
                _favoriteTabState.value = if (it.selectedMenu.data == menu) {
                    FavoriteTabState.Viewing(it.selectedMenu.copy(data = null, state = 0))
                } else {
                    FavoriteTabState.Viewing(it.selectedMenu.copy(data = menu, state = 1))
                }
            }
        }
    }

    fun unselectFavorite() {
        _favoriteTabState.value.let {
            if (it is FavoriteTabState.Viewing) {
                _favoriteTabState.value = FavoriteTabState.Viewing()
            }
        }
    }

    fun setFavoriteQuantity(quantity: Int) {
        _favoriteTabState.value.let {
            if (it is FavoriteTabState.Viewing) {
                _favoriteTabState.value = FavoriteTabState.Viewing(it.selectedMenu.copy(state = quantity))
            }
        }
    }

    fun enterEditMode() {
        _favoriteTabState.value = FavoriteTabState.Editing()
    }

    fun exitEditMode() {
        _favoriteTabState.value = FavoriteTabState.Viewing()
    }

    fun toggleFavoritesToEdit(menu: Menu) {
        _favoriteTabState.value.let {
            if (it is FavoriteTabState.Editing) {
                _favoriteTabState.value = FavoriteTabState.Editing(it.checkedMenus.toMutableSet().apply {
                    if (contains(menu)) {
                        remove(menu)
                    } else {
                        add(menu)
                    }
                })
            }
        }
    }

    suspend fun removeFavoritesToEdit() {
        _favoriteTabState.value.let {
            if (it is FavoriteTabState.Editing) {
                it.checkedMenus.forEach { menu ->
                    favoritesRepository.removeFromFavorites(menu.id)
                }
            }
        }
    }
}