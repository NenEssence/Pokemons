package com.example.pokemons.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.presentation.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private lateinit var bindingDetails: FragmentPokemonDetailsBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    lateinit var pokemon:Pokemon
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch{
            val pokemonId = args.pokeminId
            pokemon = viewModel.getPokemonById(pokemonId)
            bindingDetails = FragmentPokemonDetailsBinding.bind(view)
            if(pokemon!=null) {
                Glide.with(this@PokemonDetailsFragment)
                    .load(pokemon.imageFile)
                    .into(bindingDetails.pokemonDetailsImageView)
                bindingDetails.nameIdTextView.text = pokemon.name.plus(" #").plus(String.format("%04d", pokemon.id))
                bindingDetails.typeTextView.text = pokemon.type
                val heightString = pokemon.height.toString() + "  dm"
                bindingDetails.hightTextView.text = heightString
                val weightString = pokemon.weight.toString() + "  kg"
                bindingDetails.weightTextView.text = weightString
            }
            }

    }
}