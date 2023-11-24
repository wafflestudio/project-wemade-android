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
import androidx.compose.ui.tooling.preview.Preview
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.component.BottomBarButton
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.component.TopBarButton
import com.wafflestudio.projectwemade.model.dto.Menu

@Composable
fun CheckoutScreen(
    menuList: List<Menu>,
    modifier: Modifier = Modifier
) {
    Column(
       modifier = modifier.fillMaxSize()
    ) {
        CenterTopBar (
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

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "주문 내역")
            for(menu in menuList){
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
                                Text(text = menu.price.toString())
                            }
                        )
                    }
                )
            }
        }

        BottomBar (
            modifier = Modifier.fillMaxWidth(),
            leftComposable = {},
            rightComposable = {
                BottomBarButton(
                    onClick = {},
                    text = "주문하기",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Composable
@Preview
fun CheckoutScreenPreview(){
    CheckoutScreen(
        listOf(
            Menu(
                name = "아메리카노",
                price = 4000,
                image = "https://imageurl"
            ),
            Menu(
                name = "카페라떼",
                price = 5000,
                image = "https://imageurl"
            ),
            Menu(
                name = "아메리카노",
                price = 4000,
                image = "https://imageurl"
            ),
            Menu(
                name = "카페라떼",
                price = 5000,
                image = "https://imageurl"
            ),
            Menu(
                name = "아메리카노",
                price = 4000,
                image = "https://imageurl"
            ),
            Menu(
                name = "카페라떼",
                price = 5000,
                image = "https://imageurl"
            ),
            Menu(
                name = "아메리카노",
                price = 4000,
                image = "https://imageurl"
            ),
            Menu(
                name = "카페라떼",
                price = 5000,
                image = "https://imageurl"
            ),
            Menu(
                name = "아메리카노",
                price = 4000,
                image = "https://imageurl"
            ),
            Menu(
                name = "카페라떼",
                price = 5000,
                image = "https://imageurl"
            )
        )
    )
}