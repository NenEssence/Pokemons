package com.example.pokemons.Presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemons.PokemonAdapter
import com.example.pokemons.Data.PokemonList
import com.example.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = PokemonAdapter(PokemonList.pokemonList)
        adapter.onClick = { openDetailActivity(it)}

        binding?.rvList?.adapter = adapter

    }


    private fun openDetailActivity(position: Int){
        val intent = Intent(this,PokemonDetailsActivity::class.java)
        intent.putExtra("pokemonPos",position)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}