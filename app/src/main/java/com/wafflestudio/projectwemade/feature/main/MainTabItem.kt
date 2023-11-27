package com.wafflestudio.projectwemade.feature.main

import com.wafflestudio.projectwemade.R

sealed class MainTabItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    object Home : MainTabItem(
        route = "home",
        title = "홈",
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outline
    )
    object Order : MainTabItem(
        route = "order",
        title = "주문하기",
        selectedIcon = R.drawable.ic_drink_filled,
        unselectedIcon = R.drawable.ic_drink_outline
    )
    object Mypage : MainTabItem(
        route = "mypage",
        title = "마이페이지",
        selectedIcon = R.drawable.ic_mypage_filled,
        unselectedIcon = R.drawable.ic_mypage_outline
    )
}