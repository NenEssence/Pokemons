package com.example.pokemons.presentation.details

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemons.databinding.ActivityPokemonDetailsBinding
import com.example.pokemons.presentation.MakePokemonParcelable

class PokemonDetailsActivity: AppCompatActivity() {
      private lateinit var bindingDetails: ActivityPokemonDetailsBinding
      private var parcedData: MakePokemonParcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parcedData = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra("pokemonModel", MakePokemonParcelable::class.java)
        }else{
            intent.getParcelableExtra("pokemonModel")
        }

        bindingDetails = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(bindingDetails.root)

        val detailsPokemon = parcedData?.pokemon

        bindingDetails.pokemonDetailsImageView.setImageResource(detailsPokemon!!.imageFile)
        bindingDetails.nameIdTextView.text =  detailsPokemon.name.plus(" #").plus(String.format("%04d",detailsPokemon.id))
        bindingDetails.typeTextView.text = detailsPokemon.type
        bindingDetails.hightTextView.text = detailsPokemon.hight.toString()
        bindingDetails.weightTextView.text = detailsPokemon.weight.toString()

    }
}