package com.wafflestudio.projectwemade.feature.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.common.LocalBottomSurfaceState
import com.wafflestudio.projectwemade.common.LocalNavController
import com.wafflestudio.projectwemade.component.CenterTopBar
import com.wafflestudio.projectwemade.component.Chip
import com.wafflestudio.projectwemade.component.FavoriteMenuCard
import com.wafflestudio.projectwemade.component.MenuCard
import com.wafflestudio.projectwemade.model.dto.Category
import com.wafflestudio.projectwemade.theme.WemadeColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val bottomSurfaceState = LocalBottomSurfaceState.current
    val scope = rememberCoroutineScope()

    val pages = listOf("Favorite", "메뉴")
    val pagerState = rememberPagerState(pageCount = { 2 })

    val menus by orderViewModel.menus.collectAsState()
    val selectedCategory by orderViewModel.selectedCategory.collectAsState()
    val categoryMenus by orderViewModel.categoryMenus.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterTopBar(
            title = stringResource(R.string.order_app_bar)
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .background(WemadeColors.White900)
                            .padding(start = 20.dp, end = 20.dp, top = 22.dp)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(menus.size) { index ->
                                FavoriteMenuCard(
                                    menu = menus[index],
                                    onClick = {
                                        bottomSurfaceState.visible = true
                                    }
                                )
                            }
                        }
                    }
                }

                1 -> {
                    Column(
                        modifier = Modifier
                            .background(WemadeColors.White900)
                            .padding(start = 20.dp, end = 20.dp, top = 13.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(22.dp)
                        ) {
                            Category.values().forEach {
                                Chip(
                                    text = it.toString(),
                                    selected = it == selectedCategory,
                                    onClick = {
                                        orderViewModel.selectCategory(it)
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(categoryMenus.size) { index ->
                                MenuCard(categoryMenus[index])
                            }
                        }
                    }
                }
            }
        }

    }
}