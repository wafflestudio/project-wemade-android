package com.wafflestudio.projectwemade.common

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    throw RuntimeException("")
}

val LocalBottomBarState = compositionLocalOf<BottomBarState> {
    throw RuntimeException("")
}