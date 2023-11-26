package com.wafflestudio.projectwemade.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy

//@Stable
//interface BottomBarState {
//    var isVisible: Boolean
//    var content: @Composable () -> Unit
//}
//
//private class BottomBarStateImpl(
//    isVisible: Boolean = false,
//    content: @Composable () -> Unit = {},
//) : BottomBarState {
//
//    private var _isVisible by mutableStateOf(isVisible)
//
//    override var isVisible: Boolean
//        get() = _isVisible
//        set(value) { _isVisible = value }
//
//    private var _content: @Composable () -> Unit by mutableStateOf(content)
//
//    override var content
//        get() = _content
//        set(value) { _content = value }
//}
//
//fun BottomBarState(): BottomBarState {
//    return BottomBarStateImpl()
//}

@Stable
class BottomBarState() {
    var isVisible by mutableStateOf(false, structuralEqualityPolicy())
    var content by mutableStateOf<@Composable () -> Unit>({}, structuralEqualityPolicy())
}

@Composable
fun rememberBottomBarState() = remember { BottomBarState() }