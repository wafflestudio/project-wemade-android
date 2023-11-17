package com.wafflestudio.projectwemade.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CenterTopBar(
    leftButton: @Composable() () -> Unit,
    rightButton: @Composable() () -> Unit,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leftButton()
        Text(text = text)
        rightButton()
    }
}

@Preview
@Composable
fun CenterTopBarPreview(){
    CenterTopBar(
        leftButton = { Button(onClick = {}) { Text ("left")} },
        rightButton = { Button(onClick = {}) { Text ("right")} },
        text = "Text"
    )
}
