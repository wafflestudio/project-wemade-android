package com.wafflestudio.projectwemade.util

import androidx.navigation.NavController

fun NavController.navigateAsOrigin(route: String) {
    navigate(route) {
        while(popBackStack()) {
            // pop back until end
        }
        launchSingleTop = true
    }
}