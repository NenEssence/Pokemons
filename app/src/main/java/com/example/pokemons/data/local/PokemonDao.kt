package com.example.pokemons.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDbEntity)

    @Query("SELECT * FROM pokemon WHERE id=:id ")
    suspend fun getPokemonById(id: Int): PokemonDbEntity

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): Flow<List<PokemonDbEntity>>

    @Query("SELECT COUNT(*) FROM pokemon")
    fun getPokemonCount(): Int
}