package com.example.pokemons.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pokemons.R
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.presentation.rv.RvFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PokemonAdapter
    private lateinit var navController: NavController
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = dependencyContainer.adapter
        adapter.onClick = { openDetailFragment(it)}

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        viewModel.loadData(dependencyContainer.repository)
    }



    private fun openDetailFragment(pokemonId: Int){
        var argument = pokemonId
        val action = RvFragmentDirections.actionRvFragmentToPokemonDetailsFragment(argument)
        navController.navigate(action)
    }
}