package com.example.pokemons.data.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemons.data.PokemonRepository
import javax.inject.Inject


class UpdateWorker(appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {
    @Inject
    lateinit var repository: PokemonRepository
    override suspend fun doWork(): Result {
        val pokemonDbCount = repository.getPokemonCount()
        if(pokemonDbCount!=0){
            for(i in 1.. pokemonDbCount){
               repository.insertPokemon(
                    repository.getPokemonById(i)
                )
            }
        }
        else{
            for(i in 1.. 20){
                repository.insertPokemon(
                    repository.getPokemonById(i)
                )
            }
        }
        return Result.success()
    }
}