package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LeftTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleLarge
        )
        actions()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 600)
@Composable
fun LeftTopBarPreview() {
    LeftTopBar(
        title = "주문하기/메뉴"
    ) {
        Chip(text = "검색 버튼")
        Chip(text = "장바구니")
    }
}