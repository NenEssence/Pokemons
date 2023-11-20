package com.example.pokemons.data

import com.example.pokemons.data.local.Mapper
import com.example.pokemons.data.local.PokemonDao
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.domain.entities.Pokemon
import com.example.pokemons.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonApi: PokemonApi,
    private val mapper: Mapper
) : PokemonRepository {
    suspend override fun getPokemonById(id: Int): Pokemon {
        return mapper.mapToPokemon(pokemonDao.getPokemonById(id))
    }

    override fun getAllPokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons().map { pokemonList ->
            pokemonList.map { mapper.mapToPokemon(it) }
        }
    }

    override fun getPokemonCount(): Int {
        return pokemonDao.getPokemonCount()
    }

    override suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(mapper.mapToPokemonDbEntity(pokemon))
    }

    override suspend fun loadPokemonById(id: Int): Pokemon {
        return pokemonApi.loadPokemonById(id).toPokemon()
    }

    override suspend fun updatePokemons() {
        val pokemonCount = getPokemonCount()
        for( i in 1..pokemonCount){
            insertPokemon(loadPokemonById(i))
        }
    }

    override suspend fun loadMorePokemons(){
        val currentCount = getPokemonCount()
        for (i in currentCount..currentCount + 10) {
            insertPokemon(loadPokemonById(i))
        }
    }

    override suspend fun loadStartPokemons(){
        val currentCount = getPokemonCount()
        if (currentCount == 0) {
            for (i in 1..20) {
                insertPokemon(loadPokemonById(i))
            }
        }
    }
}
