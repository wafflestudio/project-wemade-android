package com.wafflestudio.projectwemade.feature.mypage.supports

sealed class SupportsTabItem (
    val route: String,
    val title: String
) {
    object Contact: SupportsTabItem(
        route = "contact",
        title = "문의하기"
    )
    object ContactHistory: SupportsTabItem(
        route = "contactHistory",
        title = "문의내역"
    )
}