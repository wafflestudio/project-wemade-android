package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun OptionChip(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    color: Color = WemadeColors.Brown,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .background(WemadeColors.White900)
            .border(
                width = 1.dp,
                color = if (selected) color else WemadeColors.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .width(69.dp)
            .padding(vertical = 8.dp),
        color = if (selected) color else WemadeColors.DarkGray,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall
    )
}