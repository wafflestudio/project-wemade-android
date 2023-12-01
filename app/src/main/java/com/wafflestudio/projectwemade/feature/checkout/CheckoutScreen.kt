package com.wafflestudio.projectwemade.feature.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.component.BottomBarButton
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.component.TopBarButton

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "주문하기",
            leftAction = {
                TopBarButton(
                    onClick = { },
                    text = "뒤로가기"
                )
            },
            rightAction = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "주문 내역")
            checkoutViewModel.cartMenus.forEach { menu ->
                MenuCard(
                    menu = menu,
                    actionTopRight = {
                        Button(
                            onClick = {},
                            content = {
                                Text(text = "X")
                            }
                        )
                    },
                    actionBottomLeft = {
                        Button(
                            onClick = {},
                            content = {
                                Text(text = "옵션들")
                            }
                        )
                    },
                    actionBottomRight = {
                        Button(
                            onClick = {},
                            content = {
                                Text(text = "price")
                            }
                        )
                    }
                )
            }
        }

        BottomBar(
            modifier = Modifier.fillMaxWidth(),
            topComposable = {},
            bottomComposable = {
                BottomBarButton(
                    onClick = { },
                    text = "주문하기",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}