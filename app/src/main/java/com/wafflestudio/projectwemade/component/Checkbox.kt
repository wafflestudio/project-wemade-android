package com.wafflestudio.projectwemade.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.wafflestudio.projectwemade.icon.CheckboxIcon

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckChanged: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    CheckboxIcon(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onCheckChanged?.invoke(checked.not()) }
            ),
        checked = checked
    )
}