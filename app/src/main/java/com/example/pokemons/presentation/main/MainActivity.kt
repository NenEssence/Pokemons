package com.example.pokemons.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.pokemons.R
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.presentation.rv.RvFragment
import com.example.pokemons.presentation.rv.RvFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PokemonAdapter
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = dependencyContainer.adapter
        adapter.onClick = { openDetailFragment(it)}

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }



    private fun openDetailFragment(pokemon: Parcelable){
        var argument = 1
        val action = RvFragmentDirections.actionRvFragmentToPokemonDetailsFragment(argument)
        navController.navigate(action)
    }
}