package com.wafflestudio.projectwemade.util

import com.google.firebase.database.DataSnapshot
import com.wafflestudio.projectwemade.model.dto.CartedMenu

fun DataSnapshot.readCartedMenu(): CartedMenu {
    val uid = this.child("uid").getValue(String::class.java) ?: ""
    val quantity = this.child("quantity").getValue(Int::class.java) ?: 0
    val menu = this.child("menu").readMenu()
    return CartedMenu(uid, menu, quantity)
}

fun DataSnapshot.writeCartedMenu(cartedMenu: CartedMenu) {
    this.apply {
        child("uid").ref.setValue(cartedMenu.uid)
        child("menu").writeMenu(cartedMenu.menu)
        child("quantity").ref.setValue(cartedMenu.quantity)
    }
}