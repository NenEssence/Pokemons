package com.example.pokemons.di

import android.content.Context
import androidx.room.Room
import com.example.pokemons.data.PokemonRepositoryImpl
import com.example.pokemons.data.local.AppDatabase
import com.example.pokemons.data.local.Mapper
import com.example.pokemons.data.local.PokemonDao
import com.example.pokemons.data.remote.PokemonApi
import com.example.pokemons.domain.PokemonInteractor
import com.example.pokemons.domain.repository.PokemonRepository
import com.example.pokemons.presentation.rv.PokemonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideAdapter(@ApplicationContext context: Context): PokemonAdapter {
        return PokemonAdapter(context)
    }

    @Provides
    fun provideInteractor(repository: PokemonRepository): PokemonInteractor {
        return PokemonInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(
        pokemonDao: PokemonDao,
        pokemonApi: PokemonApi,
        mapper: Mapper
    ): PokemonRepository = PokemonRepositoryImpl(pokemonDao, pokemonApi, mapper)

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()

    @Provides
    fun providesPokemonDao(appDataBase: AppDatabase): PokemonDao = appDataBase.pokemonDao()

    @Provides fun provideLoggingIntercepter(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    fun providesClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesPokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    @Provides fun provideMapper(): Mapper = Mapper()
}
