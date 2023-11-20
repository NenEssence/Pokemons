package com.example.pokemons.domain.entities

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val imageFile: String,
    val height: Double,
    val weight: Double
)
