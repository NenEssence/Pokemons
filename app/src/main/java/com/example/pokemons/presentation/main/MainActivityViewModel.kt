package com.example.pokemons.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemons.data.Repository
import com.example.pokemons.domain.Pokemon

class MainActivityViewModel: ViewModel() {
    private val liveData = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = liveData

    fun loadData(newRepository: Repository) {
        liveData.postValue(newRepository.getData())
    }
}