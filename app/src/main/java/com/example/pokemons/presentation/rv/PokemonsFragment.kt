package com.example.pokemons.presentation.rv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentPokemonsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonsFragment : Fragment(R.layout.fragment_pokemons) {
    private lateinit var binding: FragmentPokemonsBinding
    private val pokemonsViewModel: PokemonsViewModel by activityViewModels()

    @Inject
    lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonsBinding.bind(view)
        binding.rvList.adapter = adapter

        pokemonsViewModel.loadStartPokemons()

        binding.rvList.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        pokemonsViewModel.loadMorePokemons()
                    }
                }
            }
        )

        binding.swiperefresh.setOnRefreshListener {
            pokemonsViewModel.updatePokemons()
        }


        adapter.onClick = {
            pokemonsViewModel.setDetailsPokemon(it)
            val action = PokemonsFragmentDirections.actionPokemonsFragmentToPokemonDetailsFragment()
            binding.root.findNavController().navigate(action)
        }


        pokemonsViewModel.getAllPokemons().asLiveData().observe(viewLifecycleOwner) { pokemonList ->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }
    }
}