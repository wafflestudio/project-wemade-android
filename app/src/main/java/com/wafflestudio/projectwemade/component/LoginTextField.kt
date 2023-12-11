package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.theme.WemadeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholderString: String = ""
) {
    TextField (
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(
                color = WemadeColors.White900,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = WemadeColors.MediumGray,
                shape = RoundedCornerShape(4.dp)
            ),
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = placeholderString,
                style = MaterialTheme.typography.bodyLarge,
                color = WemadeColors.DarkGray
            )
        }
    )
}

@Preview
@Composable
fun LoginTextFieldPreview(){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    LoginTextField(
        value = text,
        onValueChange = {newText -> text = newText},
        placeholderString = "placeholder"
    )
}