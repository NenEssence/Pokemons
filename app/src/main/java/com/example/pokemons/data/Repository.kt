package com.example.pokemons.data
import com.example.pokemons.R
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.domain.PokemonRepository

class Repository(): PokemonRepository{
    private var pokemons: List<Pokemon> = listOf(
        Pokemon(1, "Bulbasaur", "Grass, Poison", R.drawable.p001, 0.7, 6.9),
        Pokemon(2, "Ivysaur", "Grass, Poison", R.drawable.p002, 1.0, 13.0),
        Pokemon(3, "Venusaur", "Grass, Poison", R.drawable.p003, 2.0, 100.0),
        Pokemon(4, "Charmander", "Fire", R.drawable.p004, 0.6, 8.5),
        Pokemon(5, "Charmeleon", "Fire", R.drawable.p005, 1.1, 19.0),
        Pokemon(6, "Charizard", "Fire", R.drawable.p006, 1.7, 90.5),
        Pokemon(7, "Squirtle", "Water", R.drawable.p007, 0.5, 9.0),
        Pokemon(8, "Wartortle", "Water", R.drawable.p008, 1.0, 22.5),
        Pokemon(9, "Blastoise", "Water", R.drawable.p009, 1.6, 85.5),
    )
    override fun getData(): List<Pokemon> {
        return  pokemons
    }
    override fun setData(newPokemonData: List<Pokemon>) {
        pokemons = newPokemonData
    }
}