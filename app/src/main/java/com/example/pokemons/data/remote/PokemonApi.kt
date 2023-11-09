package com.example.pokemons.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun loadPokemonById(@Path("id") id: Int): PokemonApiEntity
}