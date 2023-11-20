package com.example.pokemons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
