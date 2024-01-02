package com.wafflestudio.projectwemade.model.dto

data class Menu(
    val id: Int,
    val name: String,
    val category: Category,
    val image: String,
    val availableTemperature: List<Temperature>,
    val temperature: Temperature?,
    val availableStrength: List<Strength>,
    val strength: Strength?,
) {
    fun optionSummary(): String {
        val t = temperature?.toString() ?: ""
        val s = strength?.toString() ?: ""
        return if (t.isNotEmpty() && s.isNotEmpty()) {
            "$t/$s"
        } else {
            "$t$s"
        }
    }

    companion object {
        val Default = Menu(
            id = 0,
            name = "Default Name",
            category = Category.COFFEE,
            image = "https://neurosciencenews.com/files/2023/06/coffee-brain-caffeine-neuroscincces.jpg",
            availableTemperature = listOf(Temperature.HOT),
            temperature = Temperature.HOT,
            availableStrength = listOf(Strength.LIGHT),
            strength = Strength.LIGHT,
        )
    }
}