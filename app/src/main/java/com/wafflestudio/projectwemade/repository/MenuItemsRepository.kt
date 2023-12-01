package com.wafflestudio.projectwemade.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wafflestudio.projectwemade.model.dto.Menu
import com.wafflestudio.projectwemade.model.dto.Strength
import com.wafflestudio.projectwemade.model.dto.Temperature
import com.wafflestudio.projectwemade.model.dto.toCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MenuItemsRepository @Inject constructor() {

    private val _menus = MutableStateFlow<List<Menu>>(emptyList())
    val menus: StateFlow<List<Menu>> get() = _menus

    init {
        val database =
            Firebase.database("https://wemade-project-default-rtdb.asia-southeast1.firebasedatabase.app")
        val ref = database.getReference("menu_items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _menus.value = snapshot.children.map { menu ->
                    val availableTemperature = menu.child("options")
                        .child("temperature").children.map {
                            Temperature.values()[it.getValue(Int::class.java)?.minus(1) ?: 0]
                        }
                    val availableStrength = menu.child("options")
                        .child("strength").children.map {
                            Strength.values()[it.getValue(Int::class.java)?.minus(1) ?: 0]
                        }
                    Menu(
                        id = menu.child("id").getValue(Int::class.java) ?: 0,
                        category = menu.child("category").getValue(String::class.java).orEmpty().toCategory(),
                        name = menu.child("name").getValue(String::class.java) ?: "",
                        availableTemperature = availableTemperature,
                        temperature = availableTemperature.firstOrNull(),
                        availableStrength = availableStrength,
                        strength = availableStrength.firstOrNull(),
                        image = "https://picsum.photos/200"
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: error handling
            }
        })
    }
}