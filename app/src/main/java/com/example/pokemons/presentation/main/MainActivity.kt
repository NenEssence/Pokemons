package com.example.pokemons.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.pokemons.R
import com.example.pokemons.data.workmanager.UpdateWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
