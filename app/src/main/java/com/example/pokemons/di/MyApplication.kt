package com.example.pokemons.di

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.pokemons.data.workmanager.UpdateWorker
import java.util.concurrent.TimeUnit

class  MyApplication : Application() {
    companion object{
        lateinit var dependencyContainer: DependencyContainer
    }
    override fun onCreate() {
        super.onCreate()
        dependencyContainer = DependencyContainer(this)

        val updateWorker = PeriodicWorkRequest.Builder(UpdateWorker::class.java,30, TimeUnit.MINUTES,25, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueue(updateWorker)

    }
}

