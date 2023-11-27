package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wafflestudio.projectwemade.feature.main.MainTabItem
import com.wafflestudio.projectwemade.theme.WemadeColors

private val tabItems = listOf(
    MainTabItem.Home,
    MainTabItem.Order,
    MainTabItem.Mypage
)

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column {
        Divider(thickness = 0.5.dp, color = WemadeColors.MediumGray)
        Row(
            modifier = modifier
                .background(WemadeColors.White900)
                .fillMaxWidth()
                .padding(start = 38.dp, end = 38.dp, top = 12.dp, bottom = 21.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabItems.forEach {
                val selected = it.route == currentRoute
                BottomNavigationItem(
                    title = it.title,
                    icon = if (selected) {
                        it.selectedIcon
                    } else {
                        it.unselectedIcon
                    },
                    color = if (selected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        WemadeColors.MediumGray
                    },
                    onClick = { navController.navigateInMainScreen(it.route) }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    title: String,
    icon: Int,
    color: Color,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = onClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color)
        )
        Text(
            text = title,
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

fun NavController.navigateInMainScreen(route: String) {
    navigate(route) {
        launchSingleTop = true
        popUpTo(route = MainTabItem.Home.route)
    }
}