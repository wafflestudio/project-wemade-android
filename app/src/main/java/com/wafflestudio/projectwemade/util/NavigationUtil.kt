package com.wafflestudio.projectwemade.util

import androidx.navigation.NavController

fun NavController.navigateAsOrigin(route: String) {
    navigate(route) {
        popUpTo(route = route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}