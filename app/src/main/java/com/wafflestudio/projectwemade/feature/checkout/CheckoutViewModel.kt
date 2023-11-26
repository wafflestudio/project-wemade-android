package com.wafflestudio.projectwemade.feature.checkout

import androidx.lifecycle.ViewModel
import com.wafflestudio.projectwemade.model.dto.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(

) : ViewModel() {

    val cartMenus = listOf(     //dummy
        Menu(
            name = "아메리카노",
            price = 4000,
            image = "https://imageurl"
        ),
        Menu(
            name = "카페라떼",
            price = 5000,
            image = "https://imageurl"
        ),
        Menu(
            name = "아메리카노",
            price = 4000,
            image = "https://imageurl"
        ),
        Menu(
            name = "카페라떼",
            price = 5000,
            image = "https://imageurl"
        ),
        Menu(
            name = "아메리카노",
            price = 4000,
            image = "https://imageurl"
        ),
        Menu(
            name = "카페라떼",
            price = 5000,
            image = "https://imageurl"
        ),
        Menu(
            name = "아메리카노",
            price = 4000,
            image = "https://imageurl"
        ),
        Menu(
            name = "카페라떼",
            price = 5000,
            image = "https://imageurl"
        ),
        Menu(
            name = "아메리카노",
            price = 4000,
            image = "https://imageurl"
        ),
        Menu(
            name = "카페라떼",
            price = 5000,
            image = "https://imageurl"
        )
    )
}