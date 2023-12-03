package com.wafflestudio.projectwemade.feature.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BorderButton
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.Checkbox
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.component.NumericStepper
import com.wafflestudio.projectwemade.icon.LeftArrow

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterTopBar(
            modifier = Modifier.fillMaxWidth(),
            title = "주문하기",
            leftAction = {
                LeftArrow(
                    modifier.clickable {
                        navController.navigate(NavigationRoutes.MAIN)
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
                // TODO: viewModel 구현 후 기능 넣기
                Checkbox(checked = false, onCheckChanged = {})
                Row(
                  modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "전체 선택",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    // TODO: 전체선택 기능 넣기
                    Text(
                        text = "2/2",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            checkoutViewModel.cartMenus.forEach { menu ->
                MenuCard(
                    menu = menu,
                    actionTopLeft = {
                        Checkbox(
                            checked = false,
                            onCheckChanged = {},
                            modifier = Modifier
                                .padding(top = 6.dp, start = 7.dp)
                                .size(28.dp)
                        )
                    },
                    actionBottomLeft = {
                        // TODO: 펼쳐지는 텍스트 컴포넌트 만들어 두면 편할 듯
                        Button(
                            onClick = {},
                            content = {
                                Text(text = "옵션들")
                            }
                        )
                    },
                    actionBottomRight = {
                        // TODO: 테두리 없는 NumericStepper도 따로 만드는 게 나으려나
                        NumericStepper(value = 1, onValueChanged = {})
                    }
                )
            }
            BorderButton(
                text = "메뉴 추가하기",
                onClick = { }
            )
        }

        CtaButton(
            text = "주문하기",
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}