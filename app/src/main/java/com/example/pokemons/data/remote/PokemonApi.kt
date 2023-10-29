package com.example.pokemons.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonFromApi
    @GET("pokemon/")
    suspend fun getPokemonList(): List<PokemonFromApi>
}