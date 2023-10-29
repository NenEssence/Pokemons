package com.example.pokemons.di

import android.content.Context
import androidx.room.Room
import com.example.pokemons.data.AppDatabase
import com.example.pokemons.data.Repository
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.presentation.rv.PokemonAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependencyContainer(context: Context) {

    val adapter = PokemonAdapter(context)
    val repository = Repository()
    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokemonApi = retrofit.create(PokemonApi::class.java)
}
