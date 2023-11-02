package com.example.pokemons.data.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pokemons.data.PokemonRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: PokemonRepository): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val pokemonDbCount = repository.getPokemonCount()
        if(pokemonDbCount!=0){
            for(i in 1.. pokemonDbCount){
               repository.insertPokemon(
                    repository.getPokemonById(i)
                )
            }
        }
        return Result.success()
    }
}