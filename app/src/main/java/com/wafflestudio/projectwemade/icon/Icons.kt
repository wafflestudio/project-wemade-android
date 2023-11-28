package com.wafflestudio.projectwemade.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.wafflestudio.projectwemade.R
import com.wafflestudio.projectwemade.theme.WemadeColors

@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    filled: Boolean = false,
    color: Color = WemadeColors.Black900,
) {
    Image(
        painter = painterResource(if (filled) R.drawable.ic_home_filled else R.drawable.ic_home_outline),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun DrinkIcon(
    modifier: Modifier = Modifier,
    filled: Boolean = false,
    color: Color = WemadeColors.Black900,
) {
    Image(
        painter = painterResource(if (filled) R.drawable.ic_drink_filled else R.drawable.ic_drink_outline),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun MypageIcon(
    modifier: Modifier = Modifier,
    filled: Boolean = false,
    color: Color = WemadeColors.Black900,
) {
    Image(
        painter = painterResource(if (filled) R.drawable.ic_mypage_filled else R.drawable.ic_mypage_outline),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun BagIcon(
    modifier: Modifier = Modifier,
    color: Color = WemadeColors.Black900,
) {
    Image(
        painter = painterResource(R.drawable.ic_bag),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun CheckboxIcon(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
) {
    Image(
        painter = painterResource(if (checked) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox_unchecked),
        modifier = modifier,
        contentDescription = "",
    )
}

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    color: Color
) {
    Image(
        painter = painterResource(R.drawable.ic_add),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun RemoveIcon(
    modifier: Modifier = Modifier,
    color: Color
) {
    Image(
        painter = painterResource(R.drawable.ic_remove),
        modifier = modifier,
        contentDescription = "",
        colorFilter = ColorFilter.tint(color)
    )
}