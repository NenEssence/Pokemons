package com.example.pokemons.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.databinding.ItemPokemonBinding



class PokemonViewHolder(val itemBinding: ItemPokemonBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bindItem(pokemon: Pokemon) {
        itemBinding.nameTextView.text = pokemon.name;
        itemBinding.descriptionTextView.text = pokemon.type;
        itemBinding.pokemonImageView.setImageResource(pokemon.imageFile)
        itemBinding.idTextView.text = String.format("#%04d",pokemon.id)
    }
}
