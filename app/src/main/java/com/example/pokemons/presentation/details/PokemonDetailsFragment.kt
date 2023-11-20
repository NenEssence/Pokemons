package com.example.pokemons.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.example.pokemons.presentation.rv.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private var _bindingDetails: FragmentPokemonDetailsBinding? = null
    private val bindingDetails
        get() = _bindingDetails!!

    private val pokemonsViewModel: PokemonsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingDetails = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return bindingDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonsViewModel.pokemonDetailsLiveData.observe(viewLifecycleOwner) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingDetails = null
    }
}
