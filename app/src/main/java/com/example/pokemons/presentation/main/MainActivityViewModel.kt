package com.example.pokemons.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.domain.Pokemon
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    fun insertPokemonRepository() {
        val pokemonList = dependencyContainer.repository.getData()
        viewModelScope.launch {
            pokemonList.forEach() { dependencyContainer.appDatabase.pokemonDao().insert(it) }
        }
    }
    suspend fun getPokemonById(pokemonId: Int): Pokemon {
        return dependencyContainer.appDatabase.pokemonDao().getPokemonById(pokemonId)
    }
}

