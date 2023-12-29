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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.icon.AddIcon
import com.wafflestudio.projectwemade.icon.RemoveIcon
import com.wafflestudio.projectwemade.theme.WemadeColors
import com.wafflestudio.projectwemade.util.clearFocusOnKeyboardDismiss

@Composable
fun CartNumericStepper(
    value: Int,
    onValueChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minValue: Int = 1,
    maxValue: Int = Int.MAX_VALUE
) {
    var text by remember(value) { mutableStateOf(value.toString()) }
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(WemadeColors.White900),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CartNumericStepperButton(
            onClick = { onValueChanged(value - 1) },
            modifier = Modifier.size(32.dp),
            enabled = value > minValue
        ) {
            RemoveIcon(
                color = WemadeColors.DarkGray
            )
        }
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .clearFocusOnKeyboardDismiss()
                .onFocusChanged {
                    if (it.isFocused.not()) {
                        text = text.toIntOrNull()?.let { newValue ->
                            if (newValue in minValue..maxValue) {
                                onValueChanged(newValue)
                                newValue.toString()
                            } else {
                                value.toString()
                            }
                        } ?: value.toString()
                    }
                },
            textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .width(48.dp)
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        }
        CartNumericStepperButton(
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
private fun CartNumericStepperButton(
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
            .border(
                width = 1.dp,
                color = WemadeColors.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(7.dp)
    ) {
        content()
    }
}