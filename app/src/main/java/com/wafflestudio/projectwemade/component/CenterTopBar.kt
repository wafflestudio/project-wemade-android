package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wafflestudio.projectwemade.ui.theme.WemadeColors

// TODO: 디자인 제대로 나오면 여백, 색깔 등 리소스화해서 적용

@Composable
private fun TopBarButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.background(color = WemadeColors.White900)
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 9.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Composable
fun CenterTopBar(
    leftOnClick: () -> Unit,
    leftText: String,
    rightOnClick: () -> Unit,
    rightText: String,
    mainText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = WemadeColors.Blue50),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarButton(leftOnClick, leftText)
        Text(
            text = mainText,
            fontSize = 30.sp
        )
        TopBarButton(onClick = rightOnClick, text = rightText)
    }
}

@Preview
@Composable
fun CenterTopBarPreview(){
    CenterTopBar(
        leftOnClick = {},
        leftText = "뒤로가기",
        rightOnClick = {},
        rightText = "장바구니",
        mainText = "Text"
    )
}
