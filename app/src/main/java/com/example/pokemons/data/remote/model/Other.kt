package com.example.pokemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class Other (
    @SerializedName("official-artwork") val artwork: Artwork
)