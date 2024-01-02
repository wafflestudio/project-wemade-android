package com.wafflestudio.projectwemade.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BorderButton
import com.wafflestudio.projectwemade.component.CartNumericStepper
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.Checkbox
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.theme.WemadeColors
import com.wafflestudio.projectwemade.util.navigateAsOrigin
import kotlinx.coroutines.launch

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val cartMenus by cartViewModel.cartMenus.collectAsState()
    val checkedUids by cartViewModel.checkedCartMenus.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .background(WemadeColors.ExtraLightGray)
            .fillMaxSize()
    ) {
        CenterTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "주문하기",
            leftAction = {
                LeftArrow(
                    modifier.clickable {
                        navController.popBackStack()
                    }
                )
            },
            rightAction = {}
        )
        Row(
            modifier = Modifier
                .background(WemadeColors.White900)
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = cartMenus.isNotEmpty() && checkedUids.size == cartMenus.size,
                onCheckChanged = {
                    cartViewModel.toggleCheckAll()
                }
            )
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "전체 선택 (${checkedUids.size}/${cartMenus.size})",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Divider(thickness = 0.5.dp, color = WemadeColors.NormalGray)
        Column(
            modifier = Modifier
                .background(WemadeColors.ExtraLightGray)
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            cartMenus.forEachIndexed { idx, carted ->
                Row(
                    modifier = Modifier
                        .background(WemadeColors.White900)
                        .padding(20.dp)
                ) {
                    Box {
                        Checkbox(
                            checked = checkedUids.contains(carted.uid),
                            onCheckChanged = {
                                cartViewModel.toggleCheckCartMenu(carted)
                            },
                            modifier = Modifier
                                .zIndex(1f)
                                .offset(x = (-14).dp, y = (-14).dp)
                                .size(28.dp)
                        )
                        AsyncImage(
                            model = carted.menu.image,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(64.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = carted.menu.name,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = carted.menu.optionSummary(),
                            color = WemadeColors.DarkGray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    CartNumericStepper(
                        value = carted.quantity,
                        onValueChanged = {
                            scope.launch {
                                cartViewModel.updateQuantity(
                                    carted.copy(
                                        quantity = it
                                    )
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            BorderButton(
                text = "메뉴 추가하기",
                onClick = {
                    navController.navigateAsOrigin(NavigationRoutes.MAIN)
                },
                modifier = Modifier
                    .background(WemadeColors.White900)
                    .fillMaxWidth()
                    .padding(20.dp)
            )
        }

        CtaButton(
            text = "주문하기",
            enabled = checkedUids.isNotEmpty(),
            onClick = {
                scope.launch {
                    checkedUids.forEach {
                        cartViewModel.removeFromCart(it)
                    }
                    navController.navigate(NavigationRoutes.ORDER_COMPLETE) {
                        popUpTo(NavigationRoutes.MAIN)
                    }
                }
            },
            modifier = Modifier
                .background(WemadeColors.White900)
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}