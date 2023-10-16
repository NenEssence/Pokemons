package com.example.pokemons.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val imageFile: Int,
    val hight: Double,
    val weight: Double
)

interface PokemonRepository{
    fun getData(): List<Pokemon>
}
