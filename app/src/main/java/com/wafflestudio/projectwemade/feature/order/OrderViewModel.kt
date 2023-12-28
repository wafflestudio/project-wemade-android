package com.wafflestudio.projectwemade.feature.order

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.repository.MenuItemsRepository
import com.wafflestudio.projectwemade.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val menuItemsRepository: MenuItemsRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    val menus: StateFlow<List<Menu>> get() = menuItemsRepository.menus
    val favorites: StateFlow<List<Menu>> get() = userRepository.favorites


    suspend fun removeFromFavorites(menuUid: String) {
        userRepository.removeFromFavorites(menuUid)
    }
}