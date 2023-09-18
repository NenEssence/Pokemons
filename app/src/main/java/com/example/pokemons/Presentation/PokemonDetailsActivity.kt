package com.example.pokemons.Presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemons.Data.PokemonList
import com.example.pokemons.databinding.ActivityPokemonDetailsBinding

class PokemonDetailsActivity (): AppCompatActivity() {
      var bindingDetails: ActivityPokemonDetailsBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arguments = intent.extras
        val position = arguments?.getInt("pokemonPos")
        val pokemon = PokemonList.pokemonList[position!!]

        bindingDetails = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDetails!!.root)

        bindingDetails!!.pokemonDetailsImageView.setImageResource(pokemon.imageFile)
        bindingDetails!!.nameIdTextView.text =  pokemon.name.plus(" #").plus(String.format("%04d",pokemon.id))
        bindingDetails!!.typeTextView.text = pokemon.type
        bindingDetails!!.hightTextView.text = pokemon.hight.toString()
        bindingDetails!!.weightTextView.text = pokemon.weight.toString()

    }
}