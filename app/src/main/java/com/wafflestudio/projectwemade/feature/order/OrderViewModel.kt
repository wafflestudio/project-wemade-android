package com.wafflestudio.projectwemade.feature.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.projectwemade.model.dto.Category
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import com.wafflestudio.projectwemade.util.DataWithState
import com.wafflestudio.projectwemade.util.toDataWithState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository
) : ViewModel() {

    val menus get() = menuItemsRepository.menus

    private val _selectedCategory = MutableStateFlow(Category.COFFEE)
    val selectedCategory: StateFlow<Category> get() = _selectedCategory

    val categoryMenus = combine(_selectedCategory, menus) { category, menus ->
        menus.filter { it.category == category }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _favoriteMenus = MutableStateFlow(emptyList<DataWithState<Menu, Int>>())
    val favoriteMenus: StateFlow<List<DataWithState<Menu, Int>>> get() = _favoriteMenus

    val selectedFavMenus: StateFlow<List<DataWithState<Menu, Int>>>
        get() = _favoriteMenus.map { menus ->
            menus.filter { it.state > 0 }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            menus.collect {
                if (it.isNotEmpty()) {
                    _favoriteMenus.value = it.subList(0, 5)
                        .map { menu -> menu.copy(id = menu.id + 100).toDataWithState(0) }
                }
            }
        }
    }

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun toggleFavorite(menuId: Int) {
        _favoriteMenus.value = _favoriteMenus.value.map {
            if (it.data.id == menuId) {
                it.copy(
                    state = if (it.state == 0) 1 else 0
                )
            } else {
                it
            }
        }
    }

    fun unselectAllFavorites() {
        _favoriteMenus.value = _favoriteMenus.value.map {
            it.copy(state = 0)
        }
    }

    fun setFavoriteQuantity(menuId: Int, quantity: Int) {
        _favoriteMenus.value = _favoriteMenus.value.map {
            if (it.data.id == menuId) {
                it.copy(
                    state = quantity
                )
            } else {
                it
            }
        }
    }
}