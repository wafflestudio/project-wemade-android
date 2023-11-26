package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ItemCounter(
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
    ) {
        Button(onClick = {}) {
            Text("-")
        }
        Text("1")
        Button(onClick = {}) {
            Text("+")
        }
    }
}