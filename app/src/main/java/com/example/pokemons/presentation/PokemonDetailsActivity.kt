package com.example.pokemons.presentation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.databinding.ActivityPokemonDetailsBinding

class PokemonDetailsActivity: AppCompatActivity() {
      private lateinit var bindingDetails: ActivityPokemonDetailsBinding
      private var pokemon: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemon = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra("pokemonModel", Pokemon::class.java)
        }else{
            intent.getParcelableExtra("pokemonModel")
        }

        bindingDetails = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDetails!!.root)

        bindingDetails.pokemonDetailsImageView.setImageResource(pokemon!!.imageFile)
        bindingDetails.nameIdTextView.text =  pokemon!!.name.plus(" #").plus(String.format("%04d",pokemon!!.id))
        bindingDetails.typeTextView.text = pokemon!!.type
        bindingDetails.hightTextView.text = pokemon!!.hight.toString()
        bindingDetails.weightTextView.text = pokemon!!.weight.toString()

    }
}