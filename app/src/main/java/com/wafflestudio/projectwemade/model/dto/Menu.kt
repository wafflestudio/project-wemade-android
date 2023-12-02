package com.wafflestudio.projectwemade.model.dto

data class Menu(
    val id: Int,
    val name: String,
    val category: Category,
    val image: String,
    val availableTemperature: List<Temperature>?,
    val temperature: Temperature?,
    val availableStrength: List<Strength>?,
    val strength: Strength?,
)