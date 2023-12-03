package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun MenuCard(
    menu: Menu,
    modifier: Modifier = Modifier,
    actionTopLeft: @Composable () -> Unit = {},
    actionBottomLeft: @Composable () -> Unit = {},
    actionBottomRight: @Composable () -> Unit = {},
) {
    Box (
        modifier = modifier.fillMaxWidth()
            .background(WemadeColors.White900)
    ) {
        Box(
            modifier = Modifier.align(Alignment.TopStart)
                .zIndex(1f)
        ) {
            actionTopLeft()
        }
        Row(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .background(WemadeColors.LightGray, shape = RoundedCornerShape(5.dp))
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    painter = rememberAsyncImagePainter(menu.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = menu.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Box (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.align(Alignment.BottomStart)
                    ) {
                        actionBottomLeft()
                    }
                    Box (
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        actionBottomRight()
                    }
                }
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
        actionTopLeft = {
            Checkbox(
                checked = false,
                onCheckChanged = {},
                modifier = Modifier.padding(top = 6.dp, start = 7.dp)
                    .size(28.dp)
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