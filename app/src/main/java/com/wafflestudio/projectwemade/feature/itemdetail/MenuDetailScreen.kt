package com.wafflestudio.projectwemade.feature.itemdetail

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.BottomBar
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.NumericStepper
import com.wafflestudio.projectwemade.component.OptionChip
import com.wafflestudio.projectwemade.feature.cart.CartViewModel
import com.wafflestudio.projectwemade.icon.BagIcon
import com.wafflestudio.projectwemade.icon.LeftArrow
import com.wafflestudio.projectwemade.icon.LikeIcon
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.theme.WemadeColors
import kotlinx.coroutines.launch

@Composable
fun MenuDetailScreen(
    menuId: Int,
    modifier: Modifier = Modifier,
    menuDetailViewModel: MenuDetailViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val menu by menuDetailViewModel.editingMenu.collectAsState()
    val isInFavorites by menuDetailViewModel.isInFavorites.collectAsState()
    val quantity = remember{mutableIntStateOf(1)}

    LaunchedEffect(Unit) {
        menuDetailViewModel.initializeMenu(
            menuId = menuId,
            onMenuNotFound = {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_menu_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = WemadeColors.White900)
    ) {
        CenterTopBar(
            modifier = Modifier
                .fillMaxWidth(),
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
                        navController.navigate(NavigationRoutes.CART)
                    }
                )
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = menu.image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = menu.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "상품설명 Lorem ipsum dolor sit amet consectetur.",
                    color = WemadeColors.DarkGray,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(9.dp)
                    .background(color = WemadeColors.ExtraLightGray)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "온도",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        menu.availableTemperature.forEach {
                            OptionChip(
                                text = it.toString(),
                                selected = it == menu.temperature,
                                color = if (it == Temperature.HOT) WemadeColors.HotRed else WemadeColors.IceBlue,
                                onClick = {
                                    menuDetailViewModel.setTemperature(it)
                                }
                            )
                        }
                    }
                }
                if (menu.availableStrength.size > 1) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "농도",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            menu.availableStrength.forEach {
                                OptionChip(
                                    text = it.toString(),
                                    selected = it == menu.strength,
                                    onClick = {
                                        menuDetailViewModel.setStrength(it)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        BottomBar(
            modifier = Modifier
                .fillMaxWidth(),
            topComposable = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NumericStepper(
                        value = quantity.intValue,
                        onValueChanged = {
                            quantity.intValue = it
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            },
            bottomComposable = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    LikeIcon(
                        modifier = Modifier
                            .size(48.dp)
                            .border(
                                color = if (isInFavorites) WemadeColors.MainGreen else WemadeColors.LightGray,
                                shape = RoundedCornerShape(4.dp),
                                width = 1.dp
                            )
                            .clickable {
                                if (isInFavorites) {
                                    scope.launch {
                                        menuDetailViewModel.removeFromFavorites()
                                    }
                                } else {
                                    scope.launch {
                                        menuDetailViewModel.addToFavorites()
                                    }
                                }
                            }
                            .padding(8.dp),
                        enabled = isInFavorites,
                        color = if (isInFavorites) WemadeColors.MainGreen else WemadeColors.DarkGray
                    )
                    BagIcon(
                        modifier = Modifier
                            .size(48.dp)
                            .border(
                                color = WemadeColors.LightGray,
                                shape = RoundedCornerShape(4.dp),
                                width = 1.dp
                            )
                            .padding(8.dp)
                            .clickable {
                                scope.launch {
                                    cartViewModel.addToCart(menu)
                                    Toast.makeText(
                                        context,
                                        "상품을 장바구니에 담았습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                        color = WemadeColors.DarkGray
                    )
                    CtaButton(
                        text = "주문하기",
                        onClick = {
                            navController.navigate(NavigationRoutes.ORDER_COMPLETE){
                                popUpTo(NavigationRoutes.MAIN)
                            }
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