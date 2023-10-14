package com.example.pokemons.di

import com.example.pokemons.data.Repository
import com.example.pokemons.presentation.PokemonLoaderImpl
import com.example.pokemons.presentation.rv.PokemonAdapter

class DependencyContainer {
    //adapter
    val adapter = PokemonAdapter()
    //Pokemon data
    private val repository = Repository()
    private val presenter = PokemonLoaderImpl(repository)
    val pokemonList = presenter.loadData()

}
