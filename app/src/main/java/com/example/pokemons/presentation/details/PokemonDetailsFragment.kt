package com.example.pokemons.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.presentation.rv.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private lateinit var bindingDetails: FragmentPokemonDetailsBinding
    private val pokemonsViewModel: PokemonsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetails = FragmentPokemonDetailsBinding.bind(view)
        pokemonsViewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            Glide.with(this@PokemonDetailsFragment)
                .load(it.imageFile)
                .into(bindingDetails.pokemonDetailsImageView)
            bindingDetails.nameIdTextView.text =
                it.name.plus(" #").plus(String.format("%04d", it.id))
            bindingDetails.typeTextView.text = it.type
            val heightString = it.height.toString() + "  dm"
            bindingDetails.hightTextView.text = heightString
            val weightString = it.weight.toString() + "  kg"
            bindingDetails.weightTextView.text = weightString
        }
    }
}