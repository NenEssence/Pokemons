package com.example.pokemons.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer

class UpdateWorker(appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val pokemonDbCount = dependencyContainer.appDatabase.pokemonDao().getPokemonCount()
        if(pokemonDbCount!=0){
            for(i in 1.. pokemonDbCount){
                dependencyContainer.appDatabase.pokemonDao().insertPokemon(
                    dependencyContainer.pokemonApi.getPokemonById(i).toPokemon()
                )
            }
        }
        else{
            for(i in 1.. 20){
                dependencyContainer.appDatabase.pokemonDao().insertPokemon(
                    dependencyContainer.pokemonApi.getPokemonById(i).toPokemon()
                )
            }
        }
        return Result.success()
    }
}