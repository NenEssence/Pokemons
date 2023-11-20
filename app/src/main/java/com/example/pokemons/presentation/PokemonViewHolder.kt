package com.example.pokemons.presentation

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.databinding.ItemPokemonBinding
import com.example.pokemons.domain.Pokemon

class PokemonViewHolder(val itemBinding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    var pokemonImageView: ImageView = itemBinding.pokemonImageView

    fun bindItem(pokemon: Pokemon) {
        itemBinding.nameTextView.text = pokemon.name
        itemBinding.descriptionTextView.text = pokemon.type
        itemBinding.idTextView.text = String.format("#%04d", pokemon.id)
    }
}
