package com.example.devmon.model.domain

data class Creature(
    val number: Int,
    val name: String,
    val imageUrl: String,
    val isKnown: Boolean = false,
)
