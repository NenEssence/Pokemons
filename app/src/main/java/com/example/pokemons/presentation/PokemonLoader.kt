package com.example.pokemons.presentation

import com.example.pokemons.data.Repository
import com.example.pokemons.domain.Pokemon

interface PokemonLoader{
    fun loadData():List<Pokemon>
}
class PokemonLoaderImpl(private val repository: Repository):PokemonLoader {
    override fun loadData(): List<Pokemon> {
        return repository.getData()
    }
}