package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun MenuCard(
    menu: Menu,
    modifier: Modifier = Modifier,
    actionTopRight: @Composable () -> Unit = {},
    actionBottomLeft: @Composable () -> Unit = {},
    actionBottomRight: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(WemadeColors.White900)
            .padding(horizontal = 13.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        Box(
            modifier = Modifier
                .background(WemadeColors.LightGray, shape = RoundedCornerShape(5.dp))
                .fillMaxHeight()
                .aspectRatio(1f)
        )       //AsyncImage
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = menu.name,
                    style = MaterialTheme.typography.titleLarge
                )
                actionTopRight()
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                actionBottomLeft()
                actionBottomRight()
            }
        }
    }
}

@Preview
@Composable
fun MenuCardPreview() {
    val menu = Menu(
        name = "아메리카노",
        price = 4000,
        image = "https://imageurl"
    )

    MenuCard(
        menu = menu,
        actionTopRight = {
            Text(
                text = "좋아요"
            )
        },
        actionBottomLeft = {
            Text(
                text = menu.price.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actionBottomRight = {
            Text(
                text = "장바구니 담기"
            )
        }
    )
}