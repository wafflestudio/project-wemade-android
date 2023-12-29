package com.wafflestudio.projectwemade.feature.cart

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

) : ViewModel() {

    private val _cartMenus = MutableStateFlow(listOf(Menu.Default, Menu.Default, Menu.Default))
    val cartMenus: StateFlow<List<Menu>> get() = _cartMenus
}