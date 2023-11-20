package com.example.pokemons.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.pokemons.R
import com.example.pokemons.data.workmanager.UpdateWorker
import com.example.pokemons.presentation.rv.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var adapter: PokemonAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        startWork()
    }

    private fun startWork() {
        val updateWorker =
            PeriodicWorkRequest.Builder(
                UpdateWorker::class.java,
                30,
                TimeUnit.MINUTES,
                25,
                TimeUnit.MINUTES
            )
                .build()
        WorkManager.getInstance(this).enqueue(updateWorker)
    }
}
