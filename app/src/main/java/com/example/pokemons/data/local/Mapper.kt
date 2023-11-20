package com.example.pokemons.data.local

import com.example.pokemons.domain.entities.Pokemon

class Mapper {
    fun mapToPokemon(pokemon: PokemonDbEntity): Pokemon {
        return Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            type = pokemon.type,
            imageFile = pokemon.imageFile,
            height = pokemon.height,
            weight = pokemon.weight
        )
    }

    fun mapToPokemonDbEntity(pokemon: Pokemon): PokemonDbEntity {
        return PokemonDbEntity(
            id = pokemon.id,
            name = pokemon.name,
            type = pokemon.type,
            imageFile = pokemon.imageFile,
            height = pokemon.height,
            weight = pokemon.weight
        )
    }
}
