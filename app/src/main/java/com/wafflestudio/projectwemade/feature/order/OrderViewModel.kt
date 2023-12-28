package com.wafflestudio.projectwemade.feature.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.projectwemade.model.dto.Category
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
    }
}