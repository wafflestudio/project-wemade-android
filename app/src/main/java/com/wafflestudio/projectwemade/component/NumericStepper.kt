package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.icon.AddIcon
import com.wafflestudio.projectwemade.icon.RemoveIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun NumericStepper(
    value: Int,
    onValueChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minValue: Int = 1,
    maxValue: Int = Int.MAX_VALUE
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = WemadeColors.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .background(WemadeColors.White900),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumericStepperButton(
            onClick = { onValueChanged(value - 1) },
            modifier = Modifier.size(32.dp),
            enabled = value > minValue
        ) {
            RemoveIcon(
                color = WemadeColors.DarkGray
            )
        }
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(32.dp)
                .border(width = 1.dp, color = WemadeColors.LightGray),
        ) {
            Text(
                text = value.toString(),
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        NumericStepperButton(
            onClick = { onValueChanged(value + 1) },
            modifier = Modifier.size(32.dp),
            enabled = value < maxValue
        ) {
            AddIcon(
                color = WemadeColors.DarkGray,
            )
        }
    }
}

@Composable
private fun NumericStepperButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(if (enabled) WemadeColors.White900 else WemadeColors.LightGray)
            .then(
                if (enabled) {
                    Modifier.clickable(
                        onClick = onClick,
                    )
                } else {
                    Modifier
                }
            )
            .padding(7.dp)
    ) {
        content()
    }
}