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
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuItemsRepository @Inject constructor() {

    private val database =
        Firebase.database("https://wemade-project-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val ref = database.getReference("menu_items")

    private val _menus = MutableStateFlow<List<Menu>>(emptyList())
    val menus: StateFlow<List<Menu>> get() = _menus

    init {
        ref.addValueEventListener(object : ValueEventListener {     // get raw menus
            override fun onDataChange(snapshot: DataSnapshot) {
                _menus.value = snapshot.children.map { menu ->
                    parseMenu(menu)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: error handling
            }
        })
    }

    suspend fun getMenu(
        menuId: Int,
        onMenuNotFound: () -> Unit
    ): Menu {
        return ref.orderByChild("id").equalTo(menuId.toDouble()).get().await().let {
            if (it.exists()) {
                parseMenu(it.children.first())
            } else {
                onMenuNotFound()
                Menu.Default
            }
        }
    }

    private fun parseMenu(menu: DataSnapshot): Menu {
        val availableTemperature = menu.child("options")
            .child("temperature").children.map {
                Temperature.values()[it.getValue(Int::class.java)?.minus(1) ?: 0]
            }
        val availableStrength = menu.child("options")
            .child("strength").children.map {
                Strength.values()[it.getValue(Int::class.java)?.minus(1) ?: 0]
            }
        return Menu(
            id = menu.child("id").getValue(Int::class.java) ?: 0,
            category = menu.child("category").getValue(String::class.java).orEmpty().toCategory(),
            name = menu.child("name").getValue(String::class.java) ?: "",
            availableTemperature = availableTemperature,
            temperature = availableTemperature.firstOrNull(),
            availableStrength = availableStrength,
            strength = availableStrength.firstOrNull(),
            image = menu.child("image").getValue(String::class.java) ?: "https://picsum.photos/200",
        )
    }
}