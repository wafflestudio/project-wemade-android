package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors
import com.wafflestudio.projectwemade.util.clearFocusOnKeyboardDismiss

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .clearFocusOnKeyboardDismiss()
            .onFocusEvent {
                isFocused = it.isFocused
            },
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.SemiBold
        ),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = WemadeColors.White900)
                    .border(
                        width = if (isFocused) 2.dp else 1.dp,
                        color = if (isFocused) WemadeColors.MainGreen else WemadeColors.MediumGray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(vertical = 15.dp, horizontal = 13.dp)
            ) {
                innerTextField()
                if(isFocused.not() && value.isEmpty()) {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodyLarge,
                        color = WemadeColors.DarkGray
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun LoginTextFieldPreview() {
    var text by remember { mutableStateOf("id") }
    LoginTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        hint = "placeholder"
    )
}