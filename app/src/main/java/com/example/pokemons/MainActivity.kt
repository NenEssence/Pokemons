package com.example.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.databinding.ItemPokemonBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = PokemonAdapter(PokemonList.pokemonList)
        binding?.rvList?.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}