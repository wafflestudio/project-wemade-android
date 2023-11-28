package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    color: Color = WemadeColors.Brown,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .background(color = if (selected) color else WemadeColors.White900)
            .then(
                if (selected) {
                    Modifier
                } else {
                    Modifier.border(width = 1.dp, color = WemadeColors.LightGray, shape = RoundedCornerShape(24.dp))
                }
            )
            .padding(horizontal = 12.dp, vertical = 9.dp),
        color = if (selected) WemadeColors.White900 else WemadeColors.DarkGray,
        fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Normal,
        style = MaterialTheme.typography.bodyLarge
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
            text = "에이드",
            selected = false
        )
        Chip(
            text = "에이드",
            selected = true
        )
    }
}