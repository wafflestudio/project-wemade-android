package com.wafflestudio.projectwemade.feature.order

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.theme.WemadeColors
import kotlinx.coroutines.launch

@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
) {
    val navController = LocalNavController.current
    val scope = rememberCoroutineScope()
    val favorites by orderViewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        orderViewModel.menus.collect {
            it.forEach { menu ->
                Log.d("asdf", menu.name)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.Red50)
    ) {
        Text(
            text = "Menu screen",
            modifier = Modifier.align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            (1..7).forEach {
                Text(
                    text = it.toString(),
                    modifier = Modifier
                        .clickable {
                            navController.navigate("${NavigationRoutes.MENU_DETAIL}/${it}")
                        }
                )
            }
        }
        Column {
            if (favorites.isEmpty()) {
                Text("empty")
            } else {
                favorites.forEach {
                    Text(
                        text = it.name,
                        modifier = Modifier.clickable {
                            scope.launch {
                                orderViewModel.removeFromFavorites(it.id)
                            }
                        }
                    )
                }
            }
        }
    }
}