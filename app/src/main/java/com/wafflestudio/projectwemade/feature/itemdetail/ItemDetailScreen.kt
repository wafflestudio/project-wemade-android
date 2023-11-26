package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wafflestudio.projectwemade.theme.WemadeColors
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.component.BottomBarButton
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.TopBarButton

@Composable
fun ItemDetailScreen(
    itemOptions: List<ItemOption>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxSize()
    ) {
        CenterTopBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = "아이템 상세",
            leftAction = {
                TopBarButton(
                    onClick = {},
                    text = "뒤로가기"
                )
            },
            rightAction = {
                TopBarButton(
                    onClick = {},
                    text = "장바구니"
                )
            }
        )

        Column (
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = "아이템 이름")
            Text(text = "아이템 설명")
            for(itemOption in itemOptions)
                ItemOptionCard(itemOption)
        }

        BottomBar (
            modifier = Modifier.fillMaxWidth(),
            topComposable = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    ItemCounter(modifier = Modifier.align(Alignment.CenterEnd))
                }
            },
            bottomComposable = {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    BottomBarButton(onClick = { }, text = "S2")
                    BottomBarButton(onClick = { }, text = "ㅁ")
                    BottomBarButton(
                        onClick = {},
                        text = "주문하기",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        )
    }
}

@Composable
@Preview
fun ItemDetailScreenPreview(){
    ItemDetailScreen(
        itemOptions = listOf(
            ItemOption(
                optionName = "온도",
                options = listOf("hot", "cold")
            ),
            ItemOption(
                optionName = "농도",
                options = listOf<String>("기본", "진하게", "연하게")
            )
        )
    )
}