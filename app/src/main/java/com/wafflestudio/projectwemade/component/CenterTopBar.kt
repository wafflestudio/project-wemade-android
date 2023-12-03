package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun TopBarButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
//            .background(color = WemadeColors.Blue50)
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 9.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = WemadeColors.Blue50,
//            disabledContentColor = WemadeColors.Blue50
//        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = WemadeColors.Black900
        )
    }
}

@Composable
fun CenterTopBar(
    title: String,
    modifier: Modifier = Modifier,
    leftAction: @Composable () -> Unit = {},
    rightAction: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 21.dp)
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            leftAction()
        }
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
        )
        Box(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            rightAction()
        }
    }
}

@Preview
@Composable
fun CenterTopBarPreview() {
    CenterTopBar(
        leftAction = {
            TopBarButton(
                onClick = {},
                text = "뒤로가기"
            )
        },
        rightAction = {
            TopBarButton(
                onClick = {},
                text = "장바구니"
            )
        },
        title = "Text"
    )
}
