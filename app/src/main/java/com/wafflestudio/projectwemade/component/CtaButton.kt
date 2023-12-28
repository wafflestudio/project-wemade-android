package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.ProjectWemadeAndroidTheme
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun CtaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    enabledColor: Color = MaterialTheme.colorScheme.primary,
    disabledColor: Color = WemadeColors.LightGray,
    enabledTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    disabledTextColor: Color = WemadeColors.DarkGray,
    borderColor: Color = Color(0x00ffffff),
) {
    Row(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            )
            .background(
                color = if (enabled) enabledColor else disabledColor,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = if (enabled) enabledTextColor else disabledTextColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun CtaButtonPreview() {
    ProjectWemadeAndroidTheme {
        CtaButton(
            text = "주문하기",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}