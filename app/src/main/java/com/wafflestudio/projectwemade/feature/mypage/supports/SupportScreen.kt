package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.feature.main.tabComposable
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.theme.WemadeColors
import java.util.Random

@Composable
fun SupportScreen() {
    val navController = LocalNavController.current
    val supportsScreenController = rememberNavController()
    val cardNum = Random().nextInt(5)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WemadeColors.ExtraLightGray)
    ) {
        CenterTopBar (
            title = "고객센터",
            modifier = Modifier.fillMaxWidth(),
            leftAction = {
                LeftArrow(
                    modifier = Modifier.size(32.dp)
                        .clickable { navController.popBackStack() }
                )
            }
        )
        Divider(thickness = 1.dp, color = WemadeColors.LightGray)
        SupportsNavigation(
            cardNum = cardNum,
            navController = supportsScreenController,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            NavHost(
                navController = supportsScreenController,
                startDestination = SupportsTabItem.Contact.route
            ) {
                tabComposable(SupportsTabItem.Contact.route){
                    ContactScreen()
                }
                tabComposable(SupportsTabItem.ContactHistory.route){
                    ContactHistoryScreen(cardNum)
                }
            }
        }
    }
}

@Preview
@Composable
fun SupportScreenPreview() {
    SupportScreen()
}