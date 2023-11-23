package com.example.pokemons.presentation.rv

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.domain.PokemonInteractor
import com.example.pokemons.domain.entities.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val pokemonInteractor: PokemonInteractor) :
    ViewModel() {
    private val _pokemonDetailsLiveData = MutableLiveData<Pokemon>()
    val pokemonDetailsLiveData: MutableLiveData<Pokemon> = _pokemonDetailsLiveData

    private val _loadingStateLiveData = MutableLiveData<Boolean>()
    val loadingStateLiveData: MutableLiveData<Boolean> = _loadingStateLiveData

    private val _errorStateLiveData = MutableLiveData<Boolean>()
    val errorStateLiveData: MutableLiveData<Boolean> = _errorStateLiveData

    private var canLoadMore: Boolean = true

    private val mutex = Mutex()

    fun setDetailsPokemon(pokemonId: Int) {
        CoroutineScope(Dispatchers.IO).launch() {
            _pokemonDetailsLiveData.postValue(pokemonInteractor.getPokemonById(pokemonId))
        }
    }

    private fun errorStateCall() {
        _errorStateLiveData.postValue(true)
    }

    private fun loadingStateChange(boolean: Boolean) {
        _loadingStateLiveData.postValue(boolean)
    }

    fun loadStartPokemons() {
        canLoadMore = false
        CoroutineScope(Dispatchers.IO).launch() {
            mutex.withLock {
                Log.d("debug", currentCoroutineContext().toString())
                try {
                    loadingStateChange(true)
                    pokemonInteractor.loadStartPokemons()
                } catch (e: IOException) {
                    errorStateCall()
                }
                loadingStateChange(false)
            }
            canLoadMore = true
        }
    }

    fun loadMorePokemons() {
        if (canLoadMore) {
            CoroutineScope(Dispatchers.IO).launch() {
                mutex.withLock {
                    Log.d("debug", currentCoroutineContext().toString())
                    try {
                        loadingStateChange(true)
                        pokemonInteractor.loadMorePokemons()
                    } catch (e: IOException) {
                        errorStateCall()
                    }
                    loadingStateChange(false)
                }
            }
        }
    }

    fun updatePokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            Log.d("debug", currentCoroutineContext().toString())
            try {
                loadingStateChange(true)
                pokemonInteractor.updatePokemons()
            } catch (e: IOException) {
                errorStateCall()
            }
            loadingStateChange(false)
        }
    }

    fun getAllPokemons(): Flow<List<Pokemon>> {
        return pokemonInteractor.getAllPokemons()
    }
}
