package com.wafflestudio.projectwemade.feature.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.common.LocalBottomBarState
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeScreen() {
    val bottomBarState = LocalBottomBarState.current
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
                    Log.d("asdf", bottomBarState.isVisible.toString())
                    Log.d("asdf", "home: ${bottomBarState.hashCode()}")
                    bottomBarState.content = {
                        Text(
                            text = "hello!!!",
                            modifier = Modifier.height(100.dp)
                        )
                    }
                    bottomBarState.isVisible = bottomBarState.isVisible.not()
                }
        )
    }
}