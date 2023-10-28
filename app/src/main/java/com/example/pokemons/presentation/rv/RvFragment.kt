package com.example.pokemons.presentation.rv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.asLiveData
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentRvBinding
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer

class RvFragment : Fragment(R.layout.fragment_rv) {
    private lateinit var binding: FragmentRvBinding
    private lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentRvBinding.bind(view)
        adapter = dependencyContainer.adapter
        binding.rvList.adapter = adapter


        dependencyContainer.appDatabase.pokemonDao().getAllPokemons().asLiveData().observe(viewLifecycleOwner){
                pokemonList-> adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }
    }
}
