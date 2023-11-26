package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wafflestudio.projectwemade.feature.main.MainTabItem
import com.wafflestudio.projectwemade.feature.main.navigateSingleTop

private val tabItems = listOf(
    MainTabItem.Order,
    MainTabItem.Home,
    MainTabItem.Mypage
)

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .height(66.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabItems.forEach {
            BottomNavigationItem(
                title = it.title,
                selected = it.route == currentRoute,
                onClick = { navController.navigateSingleTop(it.route) }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    //디자인에 따라 바뀔 예정
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Text(
        text = title,
        fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Normal,     //임시
        modifier = Modifier.clickable { onClick() }
    )
}