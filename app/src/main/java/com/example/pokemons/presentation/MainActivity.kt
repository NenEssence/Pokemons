package com.example.pokemons.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokemonAdapter()
        adapter.onClick = { openDetailActivity(it)}

        binding.rvList.adapter = adapter

    }

    private fun openDetailActivity(pokemon: Pokemon){
        val intent = Intent(this,PokemonDetailsActivity::class.java)
        intent.putExtra("pokemonModel",pokemon)
        startActivity(intent)
    }

}