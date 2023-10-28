package com.example.pokemons.di

import android.content.Context
import androidx.room.Room
import com.example.pokemons.data.AppDatabase
import com.example.pokemons.data.Repository
import com.example.pokemons.presentation.rv.PokemonAdapter

class DependencyContainer(context: Context) {

    val adapter = PokemonAdapter()
    val repository = Repository()
    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()
    }
}
