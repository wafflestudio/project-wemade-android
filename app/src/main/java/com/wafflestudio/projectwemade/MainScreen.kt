package com.wafflestudio.projectwemade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wafflestudio.projectwemade.component.NavigationBar

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        NavigationBar()
    }
}