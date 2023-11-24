package com.wafflestudio.projectwemade.feature.main

sealed class MainTabItem(
    val route: String,
    val title: String,
) {
    object Home : MainTabItem(route = "home", title = "홈")
    object Menu : MainTabItem(route = "menu", title = "메뉴")
    object Mypage : MainTabItem(route = "mypage", "내 정보")
}