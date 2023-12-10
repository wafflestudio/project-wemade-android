package com.wafflestudio.projectwemade.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BorderButton
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.icon.WhiteCup
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun StartScreen() {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.MainGreen)
            .padding(horizontal = 24.dp, vertical = 29.dp)
    ) {
        WhiteCup(
            modifier = Modifier
                .width(100.dp)
                .height(180.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BorderButton(
                text = "로그인",
                onClick = {
                    navController.navigate(NavigationRoutes.SIGN_IN)
                },
                modifier = Modifier.fillMaxWidth(),
                borderColor = Color(0x00ffffff),
                fontWeight = FontWeight.Bold
            )
            CtaButton(
                text = "회원가입",
                onClick = {
                    navController.navigate(NavigationRoutes.SIGN_UP)
                },
                modifier = Modifier.fillMaxWidth(),
                enabledTextColor = WemadeColors.White900,
                borderColor = WemadeColors.White900
            )
        }
    }
}