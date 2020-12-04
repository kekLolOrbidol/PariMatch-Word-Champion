package me.navid.scrambilo.model

enum class Difficult(val range: IntRange?) {
    Easy(null),
    Medium(3..5),
    Hard(8..10)
}