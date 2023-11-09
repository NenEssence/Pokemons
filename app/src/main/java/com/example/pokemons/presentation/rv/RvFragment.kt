package com.example.pokemons.presentation.rv

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentRvBinding
import com.example.pokemons.domain.PokemonInteractor
import com.example.pokemons.presentation.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@AndroidEntryPoint
class RvFragment : Fragment(R.layout.fragment_rv) {
    private lateinit var binding: FragmentRvBinding
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var adapter: PokemonAdapter
    @Inject
    lateinit var pokemonInteractor: PokemonInteractor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRvBinding.bind(view)
        binding.rvList.adapter = adapter



        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    binding.progressBar.visibility = View.VISIBLE
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            viewModel.insertPokemonDataFromApi(
                                viewModel.loadMorePokemons(
                                    adapter.itemCount, 10
                                )
                            )
                        }
                        catch (e: IOException){
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(
                                    context,
                                    "No internet connection.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        })

        binding.swiperefresh.setOnRefreshListener {
            CoroutineScope(Dispatchers.IO).launch {
                    try {
                        viewModel.insertPokemonDataFromApi(
                            if (adapter.itemCount != 0) {
                                viewModel.loadNumberOfPokemons(adapter.itemCount)
                            } else {
                                viewModel.loadNumberOfPokemons(20)
                            }
                        )
                }
                catch(e: IOException) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                binding.swiperefresh.isRefreshing = false
                }
                }


        pokemonInteractor.getAllPokemons().asLiveData().observe(viewLifecycleOwner){
                pokemonList-> adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }
    }
}
