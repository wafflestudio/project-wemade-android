package com.wafflestudio.projectwemade.feature.main

sealed class MainTabItem(
    val route: String,
    val title: String,
) {
    object Home : MainTabItem(route = "home", title = "홈")
    object Order : MainTabItem(route = "order", title = "주문하기")
    object Mypage : MainTabItem(route = "mypage", "내 정보")
}