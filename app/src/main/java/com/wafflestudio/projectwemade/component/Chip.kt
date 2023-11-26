package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) WemadeColors.Blue50 else WemadeColors.LightGray)
            .padding(horizontal = 12.dp, vertical = 9.dp),
        color = if (selected) WemadeColors.Black900 else WemadeColors.White900,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun ChipPreview() {
    Row(
        modifier = Modifier.background(WemadeColors.White900)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Chip(
            text = "좋아요 표시한 메뉴",
            selected = false
        )
        Chip(
            text = "coffee",
            selected = true
        )
    }
}