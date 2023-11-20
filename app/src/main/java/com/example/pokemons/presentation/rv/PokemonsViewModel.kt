package com.example.pokemons.presentation.rv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.domain.PokemonInteractor
import com.example.pokemons.domain.entities.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val pokemonInteractor: PokemonInteractor) :
    ViewModel() {
    private val mutex = Mutex()

    private val _pokemonDetailsLiveData = MutableLiveData<Pokemon>()
    val pokemonDetailsLiveData: MutableLiveData<Pokemon> = _pokemonDetailsLiveData

    private val _loadingStateLiveData = MutableLiveData<Boolean>()
    val loadingStateLiveData: MutableLiveData<Boolean> = _loadingStateLiveData

    private val _errorStateLiveData = MutableLiveData<Boolean>()
    val errorStateLiveData: MutableLiveData<Boolean> = _errorStateLiveData

    fun setDetailsPokemon(pokemonId: Int) {
        CoroutineScope(Dispatchers.IO).launch() {
            _pokemonDetailsLiveData.postValue(pokemonInteractor.getPokemonById(pokemonId))
        }
    }


    fun loadStartPokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            mutex.withLock {
                try {
                    _loadingStateLiveData.postValue(true)
                    pokemonInteractor.loadStartPokemons()
                    _loadingStateLiveData.postValue(false)
                } catch (e: IOException) {
                    _loadingStateLiveData.postValue(false)
                    _errorStateLiveData.postValue(true)
                }
            }
        }
    }

    fun loadMorePokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            mutex.withLock {
                try {
                    _loadingStateLiveData.postValue(true)
                    pokemonInteractor.loadMorePokemons()
                    _loadingStateLiveData.postValue(false)
                } catch (e: IOException) {
                    _loadingStateLiveData.postValue(false)
                    _errorStateLiveData.postValue(true)
                }
            }
        }
    }

    fun updatePokemons() {
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                _loadingStateLiveData.postValue(true)
                pokemonInteractor.updatePokemons()
                _loadingStateLiveData.postValue(false)
            } catch (e: IOException) {
                _loadingStateLiveData.postValue(false)
                _errorStateLiveData.postValue(true)
            }
        }
    }

    fun getAllPokemons(): Flow<List<Pokemon>> {
        _loadingStateLiveData.postValue(true)
        return pokemonInteractor.getAllPokemons()
    }


}