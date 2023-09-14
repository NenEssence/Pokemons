package com.example.pokemons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val imageFile: Int,

    val hight: Double,
    val weight: Double
) : Parcelable
