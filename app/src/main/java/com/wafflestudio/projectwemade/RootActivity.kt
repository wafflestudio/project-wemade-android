package com.wafflestudio.projectwemade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.feature.checkout.CheckoutScreen
import com.wafflestudio.projectwemade.feature.itemdetail.ItemDetailScreen
import com.wafflestudio.projectwemade.feature.main.MainScreen
import com.wafflestudio.projectwemade.theme.ProjectWemadeAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupUI()
        }
    }
}

@Composable
fun SetupUI() {
    val navController = rememberNavController()
    ProjectWemadeAndroidTheme {
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.MAIN
            ) {
                rootComposable(NavigationRoutes.MAIN) {
                    MainScreen()
                }
                rootComposable(NavigationRoutes.ITEM_DETAIL) {
                    ItemDetailScreen()
                }
                rootComposable(NavigationRoutes.CHECKOUT) {
                    CheckoutScreen()
                }
            }
        }
    }
}

fun NavGraphBuilder.rootComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                )
            )
        },
        exitTransition = {
            fadeOut(targetAlpha = 0f)
        },
        popEnterTransition = {
            fadeIn(initialAlpha = 0f)
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                )
            )
        },
        content = content
    )
}