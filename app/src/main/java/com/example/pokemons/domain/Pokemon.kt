package com.example.pokemons.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val imageFile: Int,
    val hight: Double,
    val weight: Double
)
interface PokemonRepository{
    fun getData(): List<Pokemon>
    fun setData(newPokemonData: List<Pokemon>)
}
