package com.wafflestudio.projectwemade.feature.mypage.supports

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wafflestudio.projectwemade.theme.WemadeColors

private val tabItems = listOf(
    SupportsTabItem.Contact,
    SupportsTabItem.ContactHistory
)
private val weightEach = 1f / tabItems.size
@Composable
fun SupportsNavigation(
    cardNum: Int,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Row (
        modifier = modifier.fillMaxWidth()
    ) {
        tabItems.forEach{
            val selected = it.route == currentRoute
            Column (
                modifier = Modifier.weight(weightEach)
                    .clickable {
                        navController.navigateInSupportsScreen(it.route)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = if(selected) WemadeColors.Black900
                        else WemadeColors.MediumGray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    if(it.route == "contactHistory" && cardNum != 0){
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = cardNum.toString(),
                            color = WemadeColors.White900,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.background(
                                    color = WemadeColors.MainGreen,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .padding(horizontal = 6.dp)
                        )
                    }
                }
                Divider(
                    thickness = 1.dp,
                    color = if(selected) WemadeColors.Black900
                    else WemadeColors.LightGray
                )
            }
        }
    }
}

fun NavController.navigateInSupportsScreen(route: String){
    navigate(route) {
        launchSingleTop = true
        popUpTo(SupportsTabItem.Contact.route)
    }
}