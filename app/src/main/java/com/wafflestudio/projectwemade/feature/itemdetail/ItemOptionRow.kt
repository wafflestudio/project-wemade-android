package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.OptionChip
import com.wafflestudio.projectwemade.theme.WemadeColors

data class ItemOption(
    val optionName: String,
    val options: List<String>,
    val checkedOption: MutableState<Int>
)

@Composable
fun OptionChips(
    itemOption: ItemOption,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemOption.options.forEachIndexed { idx, option ->
            OptionChip(
                text = option,
                color = if(option == "HOT") WemadeColors.HotRed
                else if(option == "ICED") WemadeColors.IceBlue
                else WemadeColors.Brown,
                selected = (idx == itemOption.checkedOption.value),
                onClick = {
                    if (idx == itemOption.checkedOption.value)
                        itemOption.checkedOption.value = -1
                    else itemOption.checkedOption.value = idx
                }
            )
        }
    }
}

@Composable
fun ItemOptionRow (
    itemOption: ItemOption,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemOption.optionName,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        OptionChips(itemOption)
    }
}