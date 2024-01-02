package com.wafflestudio.projectwemade.util

import com.google.firebase.database.DataSnapshot
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Strength
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.model.dto.toCategory

fun DataSnapshot.readMenu(): Menu {
    val availableTemperature = this.child("options")
        .child("temperature").children.map { t ->
            Temperature.values()[t.getValue(Int::class.java)?.minus(1) ?: 0]
        }
    val availableStrength = this.child("options")
        .child("strength").children.map { s ->
            Strength.values()[s.getValue(Int::class.java)?.minus(1) ?: 0]
        }
    val selectedTemperature = Temperature.values()[this.child("selected_options")
        .child("temperature").getValue(Int::class.java)?.minus(1) ?: 0]
    val selectedStrength = Strength.values()[this.child("selected_options")
        .child("strength").getValue(Int::class.java)?.minus(1) ?: 0]

    return Menu(
        id = this.child("id").getValue(Int::class.java) ?: 0,
        category = this.child("category").getValue(String::class.java).orEmpty()
            .toCategory(),
        name = this.child("name").getValue(String::class.java) ?: "",
        availableTemperature = availableTemperature,
        temperature = selectedTemperature,
        availableStrength = availableStrength,
        strength = selectedStrength,
        image = this.child("image").getValue(String::class.java)
            ?: "https://picsum.photos/200",
    )
}

fun DataSnapshot.writeMenu(menu: Menu) {
    this.ref.apply {
        child("id").setValue(menu.id)
        child("name").setValue(menu.name)
        child("category").setValue(menu.category.toString())
        child("image").setValue(menu.image)
        child("options").child("temperature").setValue(
            menu.availableTemperature.map { it.ordinal + 1 }
        )
        child("options").child("strength").setValue(
            menu.availableStrength.map { it.ordinal + 1 }
        )
        child("selected_options").child("temperature").setValue(
            (menu.temperature?.ordinal ?: 0) + 1
        )
        child("selected_options").child("strength").setValue(
            (menu.strength?.ordinal ?: 0) + 1
        )
    }
}