package com.example.pokemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class Artwork(
    @SerializedName("front_default") val picture: String
)