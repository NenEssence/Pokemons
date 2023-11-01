package com.example.pokemons.presentation.rv

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.data.PokemonRepository
import com.example.pokemons.databinding.FragmentRvBinding
import com.example.pokemons.presentation.main.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RvFragment : Fragment(R.layout.fragment_rv) {
    private lateinit var binding: FragmentRvBinding
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject
    lateinit var adapter: PokemonAdapter
    @Inject
    lateinit var repository: PokemonRepository

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
                        viewModel.insertPokemonDataFromApi(viewModel.loadMorePokemons(
                            adapter.itemCount,10))
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        })

        binding.swiperefresh.setOnRefreshListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (isOnline(view.context)) {
                    viewModel.insertPokemonDataFromApi(
                        if(adapter.itemCount!=0){
                            viewModel.loadNumberOfPokemons(adapter.itemCount)
                        }
                    else{
                            viewModel.loadNumberOfPokemons(20)
                    })
                }
                    binding.swiperefresh.isRefreshing = false
                }
        }

        repository.getAllPokemons().asLiveData().observe(viewLifecycleOwner){
                pokemonList-> adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }


}
