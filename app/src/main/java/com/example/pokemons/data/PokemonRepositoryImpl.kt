package com.example.pokemons.data
import com.example.pokemons.data.local.Mapper
import com.example.pokemons.data.local.PokemonDao
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


@Singleton
class PokemonRepositoryImpl (private val pokemonDao: PokemonDao, private val pokemonApi: PokemonApi,
private val mapper: Mapper): PokemonRepository {
    override suspend fun getPokemonById(id: Int): Pokemon {
        return mapper.mapToPokemon(pokemonDao.getPokemonById(id))
    }
    override fun getAllPokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons().map { pokemonList -> pokemonList.map{
            mapper.mapToPokemon(it)
        } }
    }
    override fun getPokemonCount(): Int {
        return pokemonDao.getPokemonCount()
    }
    override suspend fun insertPokemon(pokemon: Pokemon) {
        return pokemonDao.insertPokemon(mapper.mapToPokemonDbEntity(pokemon))
    }
    override suspend fun loadPokemonById(id: Int): Pokemon {
        return pokemonApi.loadPokemonById(id).toPokemon()
    }

}