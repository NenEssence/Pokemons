package com.example.pokemons.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer

class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private lateinit var bindingDetails: FragmentPokemonDetailsBinding
    val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonId = args.pokeminId
        val thisPokemon = dependencyContainer.repository.getPokemonById(pokemonId)

        bindingDetails = FragmentPokemonDetailsBinding.bind(view)

        if(thisPokemon!=null) {
            bindingDetails.pokemonDetailsImageView.setImageResource(thisPokemon.imageFile)
            bindingDetails.nameIdTextView.text = thisPokemon.name.plus(" #").plus(String.format("%04d", thisPokemon.id))
            bindingDetails.typeTextView.text = thisPokemon.type
            bindingDetails.hightTextView.text = thisPokemon.hight.toString()
            bindingDetails.weightTextView.text = thisPokemon.weight.toString()
        }
    }
}