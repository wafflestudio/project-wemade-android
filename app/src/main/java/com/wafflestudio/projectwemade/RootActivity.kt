package com.wafflestudio.projectwemade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.common.rememberBottomSurfaceState
import com.wafflestudio.projectwemade.feature.checkout.CheckoutScreen
import com.wafflestudio.projectwemade.feature.itemdetail.ItemDetailScreen
import com.wafflestudio.projectwemade.feature.login.SignInScreen
import com.wafflestudio.projectwemade.feature.login.SignUpScreen
import com.wafflestudio.projectwemade.feature.login.StartScreen
import com.wafflestudio.projectwemade.feature.main.MainScreen
import com.wafflestudio.projectwemade.feature.mypage.HistoryScreen
import com.wafflestudio.projectwemade.feature.mypage.SettingsScreen
import com.wafflestudio.projectwemade.feature.mypage.supports.SupportScreen
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
    val bottomSurfaceState = rememberBottomSurfaceState()

    ProjectWemadeAndroidTheme {
        CompositionLocalProvider(
            LocalNavController provides navController,
            LocalBottomSurfaceState provides bottomSurfaceState
        ) {
            Box {
                NavHost(
                    navController = navController,
                    // startDestination = NavigationRoutes.MAIN
                    startDestination = NavigationRoutes.START
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
                    rootComposable(NavigationRoutes.HISTORY) {
                        HistoryScreen()
                    }
                    rootComposable(NavigationRoutes.SUPPORTS) {
                        SupportScreen()
                    }
                    rootComposable(NavigationRoutes.SETTINGS) {
                        SettingsScreen()
                    }
                    rootComposable(NavigationRoutes.START) {
                        StartScreen()
                    }
                    rootComposable(NavigationRoutes.SIGN_IN) {
                        SignInScreen()
                    }
                    rootComposable(NavigationRoutes.SIGN_UP) {
                        SignUpScreen()
                    }
                }
                AnimatedVisibility(
                    visible = bottomSurfaceState.visible,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    enter = slideInVertically { fullHeight -> fullHeight },
                    exit = slideOutVertically { fullHeight -> fullHeight }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shadowElevation = 20.dp
                    ) {
                        bottomSurfaceState.content()
                    }
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