package com.example.pokemons.domain

import kotlinx.coroutines.flow.Flow

interface PokemonRepository{
    suspend fun getPokemonById(id: Int): Pokemon
    fun getAllPokemons(): Flow<List<Pokemon>>
    fun getPokemonCount(): Int
    suspend fun insertPokemon(pokemon: Pokemon)
    suspend fun loadPokemonById(id: Int): Pokemon
}