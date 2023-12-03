package com.wafflestudio.projectwemade.feature.order

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository
) : ViewModel() {

    val menus: StateFlow<List<Menu>> get() = menuItemsRepository.menus
}