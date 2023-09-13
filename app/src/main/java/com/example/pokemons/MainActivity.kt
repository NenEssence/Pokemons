package com.example.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = PokemonAdapter(this,PokemonList.pokemonList)
        binding?.rvList?.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}