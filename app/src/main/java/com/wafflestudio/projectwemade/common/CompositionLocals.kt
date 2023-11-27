package com.wafflestudio.projectwemade.common

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> {
    throw RuntimeException("")
}

val LocalBottomSurfaceState = compositionLocalOf<BottomSurfaceState> {
    throw RuntimeException("")
}