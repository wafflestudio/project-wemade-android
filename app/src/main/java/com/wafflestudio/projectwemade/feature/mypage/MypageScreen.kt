package com.wafflestudio.projectwemade.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun MypageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.Red50)
    ) {
        Text(
            text = "Mypage screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}