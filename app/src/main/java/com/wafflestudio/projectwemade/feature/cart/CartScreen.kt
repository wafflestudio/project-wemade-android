package com.wafflestudio.projectwemade.feature.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BorderButton
import com.wafflestudio.projectwemade.component.CartNumericStepper
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.Checkbox
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.icon.LeftArrow

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val cartMenus = cartViewModel.cartMenus.collectAsState()
    val isChecked = remember{List(cartMenus.value.size){mutableStateOf(false)}}
    val orderQuantity = remember{List(cartMenus.value.size){ mutableIntStateOf(1) } }
    Column(
        modifier = modifier.fillMaxSize()
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isChecked.filter { it.value }.size == cartMenus.value.size,
                    onCheckChanged = {
                        isChecked.forEach{it.value = true}
                    }
                )
                Row(
                  modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "전체 선택 (",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = isChecked.filter { it.value }.size.toString()
                                + "/" + cartMenus.value.size.toString() + ")",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            cartMenus.value.forEachIndexed { idx, menu ->

            }
            BorderButton(
                text = "메뉴 추가하기",
                onClick = {
                    navController.navigate(NavigationRoutes.MAIN){
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
        }

        CtaButton(
            text = "주문하기",
            onClick = {
                navController.navigate(NavigationRoutes.ORDER_COMPLETE){
                    popUpTo(NavigationRoutes.MAIN)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}