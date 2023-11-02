package com.example.pokemons.data
import com.example.pokemons.data.local.PokemonDao
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface PokemonRepository{
suspend fun getPokemonById(id: Int): Pokemon
fun getAllPokemons(): Flow<List<Pokemon>>
fun getPokemonCount(): Int
suspend fun insertPokemon(pokemon: Pokemon)
suspend fun loadPokemonById(id: Int): Pokemon
}

@Singleton
class PokemonRepositoryImpl (private val pokemonDao: PokemonDao, private val pokemonApi: PokemonApi): PokemonRepository {
    override suspend fun getPokemonById(id: Int): Pokemon {
        return pokemonDao.getPokemonById(id)
    }
    override fun getAllPokemons(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons()
    }
    override fun getPokemonCount(): Int {
        return pokemonDao.getPokemonCount()
    }
    override suspend fun insertPokemon(pokemon: Pokemon) {
        return pokemonDao.insertPokemon(pokemon)
    }
    override suspend fun loadPokemonById(id: Int): Pokemon {
        return pokemonApi.loadPokemonById(id).toPokemon()
    }

}