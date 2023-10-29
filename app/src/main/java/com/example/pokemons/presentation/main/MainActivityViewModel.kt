package com.example.pokemons.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.data.remote.PokemonFromApi
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.domain.Pokemon
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    fun insertPokemonDataFromRepository() {
        val pokemonList = dependencyContainer.repository.getData()
        viewModelScope.launch {
            pokemonList.forEach() { dependencyContainer.appDatabase.pokemonDao().insert(it) }
        }
    }
    fun insertPokemonDataFromApi(pokemonList: List<Pokemon>) {
        viewModelScope.launch {
            pokemonList.forEach() { dependencyContainer.appDatabase.pokemonDao().insert(it) }
        }
    }
    suspend fun getPokemonById(pokemonId: Int): Pokemon {
        return dependencyContainer.appDatabase.pokemonDao().getPokemonById(pokemonId)
    }

    suspend fun convertFromApiToPokemon(pokemonList: List<PokemonFromApi>): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        pokemonList.forEach(){ apiPokemon ->
            newPokemonList.add(apiPokemon.toPokemon())
        }
        return newPokemonList
    }
    suspend fun loadNumberOfPokemons(num: Int): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        for(i in 1..num){
            newPokemonList.add(dependencyContainer.pokemonApi.getPokemonById(i)
                .toPokemon())
        }
        return newPokemonList
    }
}

