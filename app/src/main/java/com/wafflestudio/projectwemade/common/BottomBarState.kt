package com.wafflestudio.projectwemade.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Stable
class BottomBarState {
    var isVisible by mutableStateOf(false)
    var content by mutableStateOf<@Composable () -> Unit>({
        Box(modifier = Modifier.height(100.dp))
    })

    override fun equals(other: Any?): Boolean {
        return (other as? BottomBarState)?.let {
            it.isVisible == isVisible && it.content == content
        } ?: false
    }

    override fun hashCode(): Int {
        return (isVisible.hashCode() * 31) + content.hashCode()
    }
}

@Composable
fun rememberBottomBarState() = remember { BottomBarState() }