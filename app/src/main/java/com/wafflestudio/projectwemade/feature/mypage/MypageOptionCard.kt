package com.wafflestudio.projectwemade.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.icon.ListIcon
import com.wafflestudio.projectwemade.icon.RightArrow

@Composable
fun MypageOptionCard(
    icon: @Composable () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleSmall
            )
        }
        RightArrow(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .width(20.dp)
                .height(20.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun MapageOptionCardPreview() {
    MypageOptionCard(
        icon = {ListIcon(modifier = Modifier.size(36.dp))},
        label = "주문내역"
    )
}