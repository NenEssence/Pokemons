package com.example.pokemons.di

import android.app.Application

class  MyApplication : Application() {
    companion object{
        lateinit var dependencyContainer: DependencyContainer
    }
    override fun onCreate() {
        super.onCreate()
        dependencyContainer = DependencyContainer()
    }
}
