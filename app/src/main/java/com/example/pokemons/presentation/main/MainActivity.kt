package com.example.pokemons.presentation.main

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
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
             permissions.entries.forEach {
                 Log.e("DEBUG", "${it.key} = ${it.value}")
             }

             if(permissions.containsValue(false)){
                 Toast.makeText(this,"Доступ к геолокации запрещен",Toast.LENGTH_SHORT).show()
             }
             else {
                 Toast.makeText(this,"Доступ к геолокации разрешен",Toast.LENGTH_SHORT).show()
                 val locationManager = LocationManager(this)
                 locationManager.getLocation()
             }
             }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationManager = LocationManager(this)
        when {
            ((this.checkSelfPermission(
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) and (this.checkSelfPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED))-> {
                locationManager.getLocation()
            }
            else -> {
                requestPermissions.launch(
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                    )
            }
        }


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