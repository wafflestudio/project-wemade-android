package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.NumericStepper
import com.wafflestudio.projectwemade.icon.BagIcon
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.icon.LikeIcon
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun ItemDetailScreen(
    modifier: Modifier = Modifier,
    itemDetailViewModel: ItemDetailViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = WemadeColors.LightGray)
    ) {
        CenterTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(WemadeColors.White900),
            title = "주문하기",
            leftAction = {
                LeftArrow(
                    modifier.clickable {
                        navController.navigate(NavigationRoutes.MAIN)
                    }
                )
            },
            rightAction = {
                BagIcon(
                    modifier = Modifier.clickable {
                        navController.navigate(NavigationRoutes.CHECKOUT)
                    }
                )
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .background(WemadeColors.White900)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = "아이템 이름",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "상품설명Lorem ipsum dolor sit amet consectetur. Posuere bibendum non nisl id",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(9.dp)
                    .background(color = WemadeColors.ExtraLightGray)
            )
            Column (
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemDetailViewModel.itemOptions.forEach { itemOption ->
                    ItemOptionRow(itemOption)
                }
            }
        }

        BottomBar(
            modifier = Modifier.fillMaxWidth()
                .background(WemadeColors.White900),
            topComposable = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NumericStepper(
                        value = 1,
                        onValueChanged = {},
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            },
            bottomComposable = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    LikeIcon(modifier = Modifier
                        .size(48.dp)
                        .border(
                            color = WemadeColors.LightGray,
                            shape = RoundedCornerShape(4.dp),
                            width = 1.dp
                        )
                        .padding(8.dp)
                    )
                    BagIcon(modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp)
                    )
                    CtaButton(
                        text = "주문하기",
                        onClick = {
                            navController.navigate(NavigationRoutes.CHECKOUT)
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                    )
                }
            }
        )
    }
}