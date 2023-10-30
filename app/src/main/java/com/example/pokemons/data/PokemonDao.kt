package com.example.pokemons.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemons.domain.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon)
    @Query("SELECT * FROM pokemon WHERE id=:id ")
    suspend fun getPokemonById(id: Int): Pokemon
    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): Flow<List<Pokemon>>
    @Query("SELECT COUNT(*) FROM pokemon")
    fun getPokemonCount(): Int

}