package com.example.pokemons.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.presentation.details.PokemonDetailsActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pokemons.presentation.LocationManager

class MainActivity : AppCompatActivity () {
    private lateinit var binding: ActivityMainBinding

     private val requestPermissions =
         registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permissions ->
             if(permissions.containsValue(false)){
                 Toast.makeText(this,"Доступ к геолокации запрещен",Toast.LENGTH_SHORT).show()
             }
             else {
                 Toast.makeText(this,"Доступ к геолокации разрешен",Toast.LENGTH_SHORT).show()
             }
             }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LocationManager(this).getLocation(requestPermissions)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PokemonAdapter()
        adapter.onClick = { openDetailActivity(it)}

        binding.rvList.adapter = adapter
    }

    private fun openDetailActivity(pokemon: Parcelable){
        val intent = Intent(this, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemonModel",pokemon)
        startActivity(intent)
    }
}