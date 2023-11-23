package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wafflestudio.projectwemade.component.Chip

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
        modifier = modifier
    ) {
        for(option in options){
            Chip(option)
        }
    }
}

@Composable
fun ItemOptionCard (
    itemOption: ItemOption,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemOption.optionName
        )
        OptionChips(itemOption.options)
    }
}

@Composable
@Preview
fun ItemOptionCardPreview(){
    ItemOptionCard(
        itemOption = ItemOption(
            optionName = "농도",
            options = listOf<String>("기본", "진하게", "연하게")
        )
    )
}