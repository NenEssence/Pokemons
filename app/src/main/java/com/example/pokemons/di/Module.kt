package com.example.pokemons.di

import android.content.Context
import androidx.room.Room
import com.example.pokemons.data.PokemonRepositoryImpl
import com.example.pokemons.data.local.AppDatabase
import com.example.pokemons.data.local.Mapper
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.domain.PokemonInteractor
import com.example.pokemons.domain.PokemonRepository
import com.example.pokemons.presentation.rv.PokemonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideAdapter(@ApplicationContext context: Context): PokemonAdapter {
        return PokemonAdapter(context)
    }
    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context): PokemonRepository {
        val appDatabase: AppDatabase by lazy {
            Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
                .build()
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val pokemonApi = retrofit.create(PokemonApi::class.java)
        val mapper = Mapper()
        return PokemonRepositoryImpl (appDatabase.pokemonDao(), pokemonApi, mapper)
        }
    @Provides
    fun provideInteractor(repository: PokemonRepository): PokemonInteractor{
        return PokemonInteractor(repository)
    }
}