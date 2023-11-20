package com.example.pokemons.domain

import com.example.pokemons.domain.entities.Pokemon
import com.example.pokemons.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonInteractor(private val repository: PokemonRepository) {
    suspend fun getPokemonById(id: Int): Pokemon {
        return repository.getPokemonById(id)
    }

    fun getAllPokemons(): Flow<List<Pokemon>> {
        return repository.getAllPokemons()
    }

    suspend fun updatePokemons() {
        return repository.updatePokemons()
    }

    suspend fun loadMorePokemons() {
        return repository.loadMorePokemons()
    }

    suspend fun loadStartPokemons() {
        return repository.loadStartPokemons()
    }
}
