package com.example.pokemons.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.presentation.main.MainActivityViewModel
import kotlinx.coroutines.launch

class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private lateinit var bindingDetails: FragmentPokemonDetailsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    val args: PokemonDetailsFragmentArgs by navArgs()

    lateinit var pokemon:Pokemon
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            val pokemonId = args.pokeminId
            pokemon = viewModel.getPokemonById(pokemonId)
            bindingDetails = FragmentPokemonDetailsBinding.bind(view)
            if(pokemon!=null) {
                bindingDetails.pokemonDetailsImageView.setImageResource(pokemon.imageFile)
                bindingDetails.nameIdTextView.text = pokemon.name.plus(" #").plus(String.format("%04d", pokemon.id))
                bindingDetails.typeTextView.text = pokemon.type
                bindingDetails.hightTextView.text = pokemon.hight.toString()
                bindingDetails.weightTextView.text = pokemon.weight.toString()
            }
            }

    }
}