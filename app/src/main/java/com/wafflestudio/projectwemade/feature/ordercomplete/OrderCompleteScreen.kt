package com.wafflestudio.projectwemade.feature.ordercomplete

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.icon.BagIcon
import com.wafflestudio.projectwemade.icon.CheckboxIcon
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun OrderCompleteScreen() {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterTopBar(
            title = "주문 완료",
            leftAction = {
                LeftArrow(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.popBackStack() }
                )
            },
            rightAction = {
                BagIcon(
                    modifier = Modifier.clickable {
                        navController.navigate(NavigationRoutes.CART) {
                            popUpTo(NavigationRoutes.MAIN)
                        }
                    }
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(WemadeColors.ExtraLightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                CheckboxIcon(
                    modifier = Modifier.size(64.dp),
                    checked = true
                )
            }
            Spacer(Modifier.height(52.dp))
            Text(
                text = "주문 완료되었습니다.",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "메뉴가 준비되기까지 기다려주세요.",
                style = MaterialTheme.typography.bodySmall,
                color = WemadeColors.MediumGray
            )
        }
    }
}