package com.wafflestudio.projectwemade.util

import androidx.navigation.NavController
import com.wafflestudio.projectwemade.NavigationRoutes

fun NavController.navigateAsOrigin(route: String) {
    navigate(route) {
        popUpTo(route = NavigationRoutes.START) {
            inclusive = true
        }
        launchSingleTop = true
    }
}