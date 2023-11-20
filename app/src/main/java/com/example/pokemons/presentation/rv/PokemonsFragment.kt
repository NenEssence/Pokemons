package com.example.pokemons.presentation.rv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private val pokemonsViewModel: PokemonsViewModel by activityViewModels()

    private var _binding: FragmentPokemonsBinding? = null
    private val binding
        get() = _binding!!

    @Inject lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonsViewModel.loadStartPokemons()

        binding.rvList.adapter = adapter

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
        binding.swiperefresh.setOnRefreshListener { pokemonsViewModel.updatePokemons() }

        adapter.onClick = {
            pokemonsViewModel.setDetailsPokemon(it)
            val action = PokemonsFragmentDirections.actionPokemonsFragmentToPokemonDetailsFragment()
            binding.root.findNavController().navigate(action)
        }

        pokemonsViewModel.loadingStateLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.swiperefresh.isRefreshing = false
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        pokemonsViewModel.errorStateLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(context, "No internet connection...", Toast.LENGTH_SHORT).show()
        }

        pokemonsViewModel.getAllPokemons().asLiveData().observe(viewLifecycleOwner) { pokemonList ->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
