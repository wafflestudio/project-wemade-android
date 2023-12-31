package com.wafflestudio.projectwemade.feature.order

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.NavigationRoutes
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.Chip
import com.wafflestudio.projectwemade.component.CtaButton
import com.wafflestudio.projectwemade.component.FavoriteMenuCard
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.component.NumericStepper
import com.wafflestudio.projectwemade.component.SimpleDialog
import com.wafflestudio.projectwemade.feature.cart.CartViewModel
import com.wafflestudio.projectwemade.icon.BagIcon
import com.wafflestudio.projectwemade.icon.LikeIcon
import com.wafflestudio.projectwemade.model.dto.Category
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.theme.WemadeColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val navController = LocalNavController.current
    val bottomSurfaceState = LocalBottomSurfaceState.current
    val scope = rememberCoroutineScope()

    val pages = listOf("Favorite", "메뉴")
    val pagerState = rememberPagerState(pageCount = { 2 })

    val selectedCategory by orderViewModel.selectedCategory.collectAsState()
    val categoryMenus by orderViewModel.categoryMenus.collectAsState()
    val favorites by orderViewModel.favorites.collectAsState()
    val favoriteTabState by orderViewModel.favoriteTabState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    val handleExitFavoriteTab = {
        if (favoriteTabState is FavoriteTabState.Editing) {
            if ((favoriteTabState as FavoriteTabState.Editing).checkedMenus.isNotEmpty()) {
                showDialog = true
            } else {
                orderViewModel.exitFavoriteEditMode()
            }
        }
    }

    BackHandler {
        if (pagerState.currentPage == 0) {
            when (favoriteTabState) {
                is FavoriteTabState.Editing -> {
                    handleExitFavoriteTab()
                }

                is FavoriteTabState.Viewing -> {
                    navController.popBackStack()
                }
            }
        } else {
            navController.popBackStack()
        }
    }

    if (showDialog) {
        SimpleDialog(
            onDismissRequest = { showDialog = false },
            title = "선택한 상품을 관심상품에서 제거할까요?",
            onClickCancel = {
                orderViewModel.exitFavoriteEditMode()
                showDialog = false
            },
            onClickOK = {
                scope.launch {
                    orderViewModel.removeFavoritesToEdit()
                    orderViewModel.exitFavoriteEditMode()
                    showDialog = false
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        orderViewModel.favoriteTabState.collect { tabState ->
            if (tabState is FavoriteTabState.Viewing && tabState.selectedMenu.data != null) {
                bottomSurfaceState.content = {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Row {
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = stringResource(R.string.order_favorite_close),
                                    modifier = Modifier.clickable {
                                        orderViewModel.unselectFavorite()
                                        bottomSurfaceState.visible = false
                                    },
                                    color = WemadeColors.DarkGray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = tabState.selectedMenu.data.name,
                                    color = WemadeColors.Black900,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = tabState.selectedMenu.data.temperature.toString(),
                                    color = when (tabState.selectedMenu.data.temperature) {
                                        Temperature.HOT -> WemadeColors.HotRed
                                        else -> WemadeColors.IceBlue
                                    },
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                NumericStepper(
                                    value = tabState.selectedMenu.state,
                                    onValueChanged = { newValue ->
                                        orderViewModel.setFavoriteQuantity(newValue)
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = WemadeColors.LightGray,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .size(48.dp)
                            ) {
                                BagIcon(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .clickable {
                                            scope.launch {
                                                cartViewModel.addToCart(
                                                    tabState.selectedMenu.data,
                                                    tabState.selectedMenu.state
                                                )
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "상품을 장바구니에 담았습니다.",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                        },
                                    color = WemadeColors.DarkGray
                                )
                            }
                            CtaButton(
                                text = stringResource(R.string.order_favorite_button),
                                onClick = {
                                    navController.navigate(NavigationRoutes.ORDER_COMPLETE) {
                                        popUpTo(NavigationRoutes.MAIN)
                                    }
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }

    bottomSurfaceState.visible =
        favoriteTabState is FavoriteTabState.Viewing && (favoriteTabState as FavoriteTabState.Viewing).selectedMenu.data != null && pagerState.currentPage == 0

    DisposableEffect(Unit) {
        onDispose { bottomSurfaceState.visible = false }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterTopBar(
            title = stringResource(R.string.order_app_bar),
            rightAction = {
                BagIcon(
                    modifier = Modifier.clickable {
                        navController.navigate(NavigationRoutes.CART)
                    }
                )
            }
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = if (pagerState.currentPage == 0) WemadeColors.MainGreen else WemadeColors.Brown
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = if (pagerState.currentPage == 0) WemadeColors.MainGreen else WemadeColors.Brown,
                    unselectedContentColor = WemadeColors.DarkGray,
                    onClick = {
                        scope.launch {
                            if (index == 1) {
                                handleExitFavoriteTab()
                            }
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = favoriteTabState is FavoriteTabState.Viewing
        ) { page ->
            when (page) {
                0 -> {
                    if (favorites.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .background(WemadeColors.White900)
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LikeIcon(
                                    modifier = Modifier.size(64.dp)
                                )
                                Spacer(modifier = Modifier.height(52.dp))
                                Text(
                                    text = "관심 메뉴를 추가해주세요.",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "주문 페이지에서 하트 아이콘을 눌러\n관심 메뉴를 추가해보세요.\n관심 메뉴에 추가된 메뉴는 바로 주문이 가능해요.",
                                    color = WemadeColors.MediumGray,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.SemiBold,
                                    lineHeight = 15.6.sp,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .background(WemadeColors.White900)
                                .fillMaxSize()
                                .padding(start = 20.dp, end = 20.dp, top = 15.dp)
                        ) {
                            if (favoriteTabState is FavoriteTabState.Editing) {
                                Text(
                                    text = "목록에서 제거",
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .clickable {
                                            if ((favoriteTabState as FavoriteTabState.Editing).checkedMenus.isNotEmpty()) {
                                                showDialog = true
                                            } else {
                                                orderViewModel.exitFavoriteEditMode()
                                            }
                                        },
                                    color = if ((favoriteTabState as FavoriteTabState.Editing).checkedMenus.isNotEmpty()) WemadeColors.MainGreen
                                    else WemadeColors.DarkGray,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textDecoration = TextDecoration.Underline
                                )
                            } else {
                                Text(
                                    text = "편집하기",
                                    modifier = Modifier
                                        .align(Alignment.End)
                                        .clickable {
                                            orderViewModel.enterFavoriteEditMode()
                                        },
                                    color = WemadeColors.DarkGray,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textDecoration = TextDecoration.Underline
                                )
                            }
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                                contentPadding = PaddingValues(vertical = 15.dp)
                            ) {
                                items(favorites.size) { index ->
                                    FavoriteMenuCard(
                                        menu = favorites[index],
                                        isInEditMode = favoriteTabState is FavoriteTabState.Editing,
                                        checked = (favoriteTabState as? FavoriteTabState.Editing)?.checkedMenus?.contains(
                                            favorites[index]
                                        ) ?: false,
                                        onClick = {
                                            if (favoriteTabState is FavoriteTabState.Viewing) {
                                                orderViewModel.toggleFavorite(favorites[index])
                                            } else {
                                                orderViewModel.toggleFavoritesToEdit(favorites[index])
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                1 -> {
                    Column(
                        modifier = Modifier
                            .background(WemadeColors.White900)
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp, top = 13.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Category.values().forEach {
                                Chip(
                                    text = it.toDisplayName(),
                                    selected = it == selectedCategory,
                                    onClick = {
                                        orderViewModel.selectCategory(it)
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            contentPadding = PaddingValues(vertical = 12.dp)
                        ) {
                            items(categoryMenus.size) { index ->
                                MenuCard(
                                    menu = categoryMenus[index],
                                    onClick = {
                                        navController.navigate("${NavigationRoutes.MENU_DETAIL}/${categoryMenus[index].id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}