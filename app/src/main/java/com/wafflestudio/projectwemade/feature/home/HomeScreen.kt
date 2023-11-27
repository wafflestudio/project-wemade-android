package com.wafflestudio.projectwemade.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeScreen() {
    val bottomSurfaceState = LocalBottomSurfaceState.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.Red50)
    ) {
        Text(
            text = "Home screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    bottomSurfaceState.content = {
                        Text(
                            text = "hello!!!",
                            modifier = Modifier.height(100.dp)
                        )
                    }
                    bottomSurfaceState.visible = bottomSurfaceState.visible.not()
                }
        )
    }
}