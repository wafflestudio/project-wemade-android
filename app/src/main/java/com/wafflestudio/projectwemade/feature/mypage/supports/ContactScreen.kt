package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun ContactScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.ExtraLightGray)
            .padding(top = 18.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
    ) {

        CtaButton(
            text = "문의하기",
            onClick = { },
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}