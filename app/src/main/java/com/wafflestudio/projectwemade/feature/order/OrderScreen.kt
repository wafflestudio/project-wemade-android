package com.wafflestudio.projectwemade.feature.order

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

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
        Text(
            text = "to itemdetail",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate(NavigationRoutes.ITEM_DETAIL)
                }
        )
    }
}