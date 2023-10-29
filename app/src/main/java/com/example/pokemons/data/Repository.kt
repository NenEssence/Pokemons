package com.example.pokemons.data
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.domain.PokemonRepository

class Repository(): PokemonRepository{
    //TODO remove
    private var pokemons: List<Pokemon> = listOf(
    )
    override fun getData(): List<Pokemon> {
        return  pokemons
    }
    override fun setData(newPokemonData: List<Pokemon>) {
        pokemons = newPokemonData
    }
}