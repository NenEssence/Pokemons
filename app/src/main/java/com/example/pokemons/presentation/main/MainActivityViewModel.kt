package com.example.pokemons.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.domain.Pokemon
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    fun insertPokemonDataFromApi(pokemonList: List<Pokemon>) {
        viewModelScope.launch {
            pokemonList.forEach() { dependencyContainer.repository.insertPokemon(it) }
        }
    }
    suspend fun getPokemonById(pokemonId: Int): Pokemon {
        return dependencyContainer.repository.getPokemonById(pokemonId)
    }

    suspend fun loadNumberOfPokemons(num: Int): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        for(i in 1..num){
            newPokemonList.add(dependencyContainer.repository.loadPokemonById(i)
                )
        }
        return newPokemonList
    }
    suspend fun loadMorePokemons(lastRv: Int): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        for(i in lastRv..lastRv+10){
            newPokemonList.add(dependencyContainer.repository.loadPokemonById(i)
                )
        }
        return newPokemonList
    }
}

