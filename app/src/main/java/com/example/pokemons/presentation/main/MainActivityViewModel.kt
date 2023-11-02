package com.example.pokemons.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.data.PokemonRepository
import com.example.pokemons.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    fun insertPokemonDataFromApi(pokemonList: List<Pokemon>) {
        viewModelScope.launch {
            pokemonList.forEach() { repository.insertPokemon(it) }
        }
    }
    suspend fun getPokemonById(pokemonId: Int): Pokemon {
        return repository.getPokemonById(pokemonId)
    }

    suspend fun loadNumberOfPokemons(num: Int): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        for(i in 1..num){
            newPokemonList.add(repository.loadPokemonById(i))
        }
        return newPokemonList
    }
    suspend fun loadMorePokemons(lastRv: Int,num: Int): List<Pokemon>{
        val newPokemonList: MutableList<Pokemon> =  mutableListOf()
        for(i in lastRv..lastRv+num){
            newPokemonList.add(repository.loadPokemonById(i))
        }
        return newPokemonList
    }
}

