package com.example.pokemons.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemons.domain.Pokemon

@Database(entities = [Pokemon::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}