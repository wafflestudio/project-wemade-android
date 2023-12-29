package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun FavoriteMenuCard(
    menu: Menu,
    isInEditMode: Boolean,
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .background(WemadeColors.White900),
    ) {
        if (isInEditMode) {
            Checkbox(
                checked = checked,
                onCheckChanged = { },
                modifier = Modifier
                    .size(28.dp)
                    .zIndex(10f)
                    .offset(x = (-10).dp, y = (-14).dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = menu.image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(165.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.height(42.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = menu.name,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    if (menu.availableTemperature.isNotEmpty()) {
                        Text(
                            text = menu.temperature.toString(),
                            color = when (menu.temperature) {
                                Temperature.HOT -> WemadeColors.HotRed
                                else -> WemadeColors.IceBlue
                            },
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (menu.availableTemperature.isNotEmpty() && menu.availableStrength.isNotEmpty()) {
                        Text(
                            text = "|",
                            modifier = Modifier.padding(horizontal = 2.dp),
                            color = WemadeColors.NormalGray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (menu.availableStrength.isNotEmpty()) {
                        Text(
                            text = menu.strength.toString(),
                            color = WemadeColors.DarkGray,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}