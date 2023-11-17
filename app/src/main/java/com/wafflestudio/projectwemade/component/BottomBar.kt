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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
private fun BottomBarButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
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
    leftComposable: @Composable () -> Unit = {},
    rightComposable: @Composable () -> Unit = {}
) {
    // TODO: add design parameters
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = WemadeColors.Red50, shape = RectangleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leftComposable()
        rightComposable()
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        BottomBar(
            //centerComposable = {Button(onClick = {}){Text(text = "Home")}},
            leftComposable = { BottomBarButton(onClick = {}, text = "left loooooooong") },
            rightComposable = { BottomBarButton(onClick = {}, text = "right") },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}