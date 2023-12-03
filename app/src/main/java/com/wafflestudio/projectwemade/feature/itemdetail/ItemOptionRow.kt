package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wafflestudio.projectwemade.component.OptionChip

data class ItemOption(
    val optionName: String,
    val options: List<String>
)

@Composable
fun OptionChips(
    options: List<String>,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for(option in options){
            OptionChip(text = option)
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
        OptionChips(itemOption.options)
    }
}

@Composable
@Preview
fun ItemOptionCardPreview(){
    ItemOptionRow(
        itemOption = ItemOption(
            optionName = "농도",
            options = listOf<String>("기본", "진하게", "연하게")
        )
    )
}