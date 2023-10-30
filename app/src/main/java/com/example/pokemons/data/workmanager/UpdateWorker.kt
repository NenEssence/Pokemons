package com.example.pokemons.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer

class UpdateWorker(appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val pokemonDbCount = dependencyContainer.repository.getPokemonCount()
        if(pokemonDbCount!=0){
            for(i in 1.. pokemonDbCount){
                dependencyContainer.repository.insertPokemon(
                    dependencyContainer.repository.getPokemonById(i)
                )
            }
        }
        else{
            for(i in 1.. 20){
                dependencyContainer.repository.insertPokemon(
                    dependencyContainer.repository.getPokemonById(i)
                )
            }
        }
        return Result.success()
    }
}