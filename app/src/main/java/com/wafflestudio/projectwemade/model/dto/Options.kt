package com.wafflestudio.projectwemade.model.dto

enum class Temperature {
    HOT,
    ICE,
}

enum class Strength {
    LIGHT,
    RICH;

    override fun toString(): String {
        return when (this) {
            LIGHT -> "연하게"
            RICH -> "진하게"
        }
    }
}