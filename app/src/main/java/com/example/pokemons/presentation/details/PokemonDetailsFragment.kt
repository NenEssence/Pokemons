package com.example.pokemons.presentation.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.presentation.MakePokemonParcelable

class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private lateinit var bindingDetails: FragmentPokemonDetailsBinding
    private var parcedData: MakePokemonParcelable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parcedData = if (Build.VERSION.SDK_INT >= 33){
            arguments?.getParcelable("pokemonData", MakePokemonParcelable::class.java)
        }else{
            arguments?.getParcelable("pokemonData")
        }

        bindingDetails = FragmentPokemonDetailsBinding.bind(view)


        val detailsPokemon = parcedData?.pokemon

        if(detailsPokemon!=null) {
            bindingDetails.pokemonDetailsImageView.setImageResource(detailsPokemon.imageFile)
            bindingDetails.nameIdTextView.text = detailsPokemon.name.plus(" #").plus(String.format("%04d", detailsPokemon.id))
            bindingDetails.typeTextView.text = detailsPokemon.type
            bindingDetails.hightTextView.text = detailsPokemon.hight.toString()
            bindingDetails.weightTextView.text = detailsPokemon.weight.toString()
        }
    }
}