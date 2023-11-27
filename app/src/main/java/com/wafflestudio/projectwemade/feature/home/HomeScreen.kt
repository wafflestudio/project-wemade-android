package com.wafflestudio.projectwemade.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.common.CtaButton
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeScreen() {
    val bottomSurfaceState = LocalBottomSurfaceState.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.White900),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home screen",
            modifier = Modifier
        )
        CtaButton(
            text = "show bottomsurface",
            onClick = {
                bottomSurfaceState.content = {
                    Text(
                        text = "hello!!!",
                        modifier = Modifier.height(100.dp)
                    )
                }
                bottomSurfaceState.visible = bottomSurfaceState.visible.not()
            }
        )
        CtaButton(
            text = "disabled",
            onClick = {
                bottomSurfaceState.content = {
                    Text(
                        text = "hello!!!",
                        modifier = Modifier.height(100.dp)
                    )
                }
                bottomSurfaceState.visible = bottomSurfaceState.visible.not()
            },
            enabled = false,
        )
    }
}