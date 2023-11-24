package com.wafflestudio.projectwemade.feature.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wafflestudio.projectwemade.component.BottomNavigation
import com.wafflestudio.projectwemade.feature.home.HomeScreen
import com.wafflestudio.projectwemade.feature.menu.MenuScreen
import com.wafflestudio.projectwemade.feature.mypage.MypageScreen

@Composable
fun MainScreen() {
    val mainScreenController = rememberNavController()      // MainScreen 내부에서 탭 이동에 쓰이는 navController
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            NavHost(
                navController = mainScreenController,
                startDestination = MainTabItem.Home.route
            ) {
                tabComposable(MainTabItem.Home.route) {
                    HomeScreen()
                }
                tabComposable(MainTabItem.Menu.route) {
                    MenuScreen()
                }
                tabComposable(MainTabItem.Mypage.route) {
                    MypageScreen()
                }
            }
        }
        BottomNavigation(navController = mainScreenController)
    }
}

fun NavGraphBuilder.tabComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        content = content
    )
}

fun NavController.navigateSingleTop(route: String) {
    navigate(route) {
        launchSingleTop = true
    }
}