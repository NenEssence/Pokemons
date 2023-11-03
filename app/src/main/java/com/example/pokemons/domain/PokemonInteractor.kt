package com.example.pokemons.domain

import kotlinx.coroutines.flow.Flow

class PokemonInteractor(private val repository: PokemonRepository) {
     suspend fun getPokemonById(id: Int): Pokemon {
        return repository.getPokemonById(id)
    }
     fun getAllPokemons(): Flow<List<Pokemon>> {
        return repository.getAllPokemons()
        }
     suspend fun insertPokemon(pokemon: Pokemon) {
        return repository.insertPokemon(pokemon)
    }
     suspend fun loadPokemonById(id: Int): Pokemon {
        return repository.loadPokemonById(id)
    }

}