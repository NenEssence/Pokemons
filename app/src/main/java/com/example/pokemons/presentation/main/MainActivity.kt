package com.example.pokemons.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.presentation.details.PokemonDetailsActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = dependencyContainer.adapter

        adapter.onClick = { openDetailActivity(it)}
        binding.rvList.adapter = adapter

        viewModel.pokemonList.observe(this){
            pokemonList-> adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }

        viewModel.loadData(dependencyContainer.repository)
    }

    private fun openDetailActivity(pokemon: Parcelable){
        val intent = Intent(this, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemonModel",pokemon)
        startActivity(intent)
    }
}