package com.wafflestudio.projectwemade.feature.itemdetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(

) : ViewModel() {

    val itemOptions = listOf(           //dummy
        ItemOption(
            optionName = "온도",
            options = listOf("hot", "cold")
        ),
        ItemOption(
            optionName = "농도",
            options = listOf<String>("기본", "진하게", "연하게")
        )
    )
}