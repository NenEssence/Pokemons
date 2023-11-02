package com.example.pokemons.di

import android.content.Context
import androidx.room.Room
import com.example.pokemons.data.local.AppDatabase
import com.example.pokemons.data.PokemonRepositoryImpl
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.presentation.rv.PokemonAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependencyContainer(context: Context) {

    val adapter = PokemonAdapter(context)

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val pokemonApi = retrofit.create(PokemonApi::class.java)

    val repository = PokemonRepositoryImpl(appDatabase.pokemonDao(), pokemonApi)
}
