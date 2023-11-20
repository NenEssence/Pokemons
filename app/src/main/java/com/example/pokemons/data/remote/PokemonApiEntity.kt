package com.example.pokemons.data.remote

import com.example.pokemons.data.remote.model.Sprite
import com.example.pokemons.data.remote.model.Type
import com.example.pokemons.domain.entities.Pokemon
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

class PokemonApiEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Double,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("sprites") val sprite: Sprite,
    @SerializedName("height") val height: Double
) {
    fun toPokemon(): Pokemon {
        val id = this.id
        val name = this.name
        var type = ""
        types.forEach() { type += " " + it.type.name }
        val imageFile = this.sprite.other.artwork.picture
        val height = this.height
        val weight = ((this.weight * 0.1) * 100).roundToInt() / 100.0
        return Pokemon(id, name, type, imageFile, height, weight)
    }
}