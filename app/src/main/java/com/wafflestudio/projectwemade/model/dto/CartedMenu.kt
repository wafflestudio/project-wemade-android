package com.wafflestudio.projectwemade.model.dto

data class CartedMenu(
    val uid: String,
    val menu: Menu,
    val quantity: Int,
)