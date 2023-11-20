package com.example.pokemons.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDbEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val imageFile: String,
    val height: Double,
    val weight: Double
)
