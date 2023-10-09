package com.example.pokemons.presentation

import com.example.pokemons.data.Repository
import com.example.pokemons.domain.Pokemon

interface PokemonPresenter{
    fun loadData():List<Pokemon>
}
class PokemonPresenterImpl(private val repository: Repository):PokemonPresenter {
    override fun loadData(): List<Pokemon> {
        return repository.getData()
    }
}