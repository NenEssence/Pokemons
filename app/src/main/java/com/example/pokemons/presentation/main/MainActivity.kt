package com.example.pokemons.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.presentation.details.PokemonDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = dependencyContainer.adapter
        adapter.setData(dependencyContainer.pokemonList)
        adapter.onClick = { openDetailActivity(it)}

        binding.rvList.adapter = adapter
    }

    private fun openDetailActivity(pokemon: Parcelable){
        val intent = Intent(this, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemonModel",pokemon)
        startActivity(intent)
    }
}