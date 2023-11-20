package com.example.pokemons.presentation.rv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {
    @Inject
    lateinit var adapter: PokemonAdapter
    private val mutex = Mutex()
    private val _pokemonLiveData = MutableLiveData<Pokemon>()
    val pokemonLiveData: MutableLiveData<Pokemon> = _pokemonLiveData


    fun setDetailsPokemon(pokemonId: Int) {
        CoroutineScope(Dispatchers.IO).launch() {
            _pokemonLiveData.postValue(pokemonRepository.getPokemonById(pokemonId))
        }
    }


    fun loadStartPokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            mutex.withLock {
                pokemonRepository.loadStartPokemons()
            }
        }
    }

    fun loadMorePokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            mutex.withLock {
                pokemonRepository.loadMorePokemons()
            }
        }
    }

    fun updatePokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            pokemonRepository.updatePokemons()
        }
    }

    fun getAllPokemons() =
        pokemonRepository.getAllPokemons()
}