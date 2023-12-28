package com.wafflestudio.projectwemade.model.dto

enum class Category {
    COFFEE,
    TEA,
    MILK,
    ADE;

    override fun toString(): String {
        return when (this) {
            COFFEE -> "커피"
            TEA -> "티"
            ADE -> "에이드"
            MILK -> "밀크"
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