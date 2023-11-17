package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wafflestudio.projectwemade.ui.theme.WemadeColors

@Composable
fun PlaceHolder(){
    Box() {}
}

@Composable
private fun BottomBarButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .padding(horizontal = 12.dp, vertical = 9.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = WemadeColors.Red50,
            disabledContainerColor = WemadeColors.Red50
        )
    ) {
        Text(
            text = text,
            color = WemadeColors.Black900
        )
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    centerComposable: @Composable () -> Unit = { PlaceHolder() },
    leftComposable: @Composable () -> Unit,
    rightComposable: @Composable () -> Unit
) {
    // TODO: add design parameters
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(color = WemadeColors.Red50, shape = RectangleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // TODO: 위쪽만 색 다른 border 적용하기
//        Canvas(modifier = Modifier) {
//            val path = Path().apply {
//                moveTo(size.width, 0f)
//                lineTo(0f, 0f)
//                lineTo(0f, size.height)
//                lineTo(size.width, size.height)
//                close()
//            }
//
//            drawPath(
//                path = path,
//                color = WemadeColors.Red200,
//                style = Stroke(width = 10.dp.toPx())
//            )
//        }
        leftComposable()
        centerComposable()
        rightComposable()
    }
}

@Preview
@Composable
fun BottomBarPreview(){
//    Box(modifier = Modifier.fillMaxSize()) {
//        BottomBarLayout(
//            centerComposable = {Button(onClick = {}){Text(text = "Home")}},
//            leftComposable = {Button(onClick = {}){Text(text = "left")}},
//            rightComposable = {Button(onClick = {}){Text(text = "right")}},
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
//    }
    Box(modifier = Modifier.fillMaxSize()) {
        BottomBar(
            //centerComposable = {Button(onClick = {}){Text(text = "Home")}},
            leftComposable = {BottomBarButton(onClick = {}, text = "left loooooooong")},
            rightComposable = {BottomBarButton(onClick = {}, text = "right")},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}