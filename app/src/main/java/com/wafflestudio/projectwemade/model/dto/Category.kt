package com.wafflestudio.projectwemade.model.dto

enum class Category {
    COFFEE,
    TEA,
    MILK,
    ADE;

    override fun toString(): String {
        return when (this) {
            COFFEE -> "Coffee"
            TEA -> "Tea"
            ADE -> "Ade"
            MILK -> "Milk"
        }
    }
}

fun String.toCategory(): Category {
    return when (this) {
        "Coffee" -> Category.COFFEE
        "Tea" -> Category.TEA
        "Milk" -> Category.MILK
        "Ade" -> Category.ADE
        else -> Category.COFFEE
    }
}