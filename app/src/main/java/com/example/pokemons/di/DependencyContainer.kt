package com.example.pokemons.di

import com.example.pokemons.data.Repository
import com.example.pokemons.presentation.rv.PokemonAdapter

class DependencyContainer {
    //adapter
    val adapter = PokemonAdapter()
    //Pokemon data
    val repository = Repository()
}
