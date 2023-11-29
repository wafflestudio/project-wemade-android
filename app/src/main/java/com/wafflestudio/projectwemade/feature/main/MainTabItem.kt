package com.wafflestudio.projectwemade.feature.main

import com.wafflestudio.projectwemade.R

sealed class MainTabItem(
    val route: String,
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    object Home : MainTabItem(
        route = "home",
        title = R.string.bottom_navigation_home,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outline
    )
    object Order : MainTabItem(
        route = "order",
        title = R.string.bottom_navigation_order,
        selectedIcon = R.drawable.ic_drink_filled,
        unselectedIcon = R.drawable.ic_drink_outline
    )
    object Mypage : MainTabItem(
        route = "mypage",
        title = R.string.bottom_navigation_mypage,
        selectedIcon = R.drawable.ic_mypage_filled,
        unselectedIcon = R.drawable.ic_mypage_outline
    )
}