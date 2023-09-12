package com.example.pokemons

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.databinding.ItemPokemonBinding



class PokemonViewHolder(val itemBinding: ItemPokemonBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bindItem(pokemon:Pokemon) {

        itemBinding.nameTextView.text = pokemon.name;
        itemBinding.descriptionTextView.text = pokemon.description;

    }
}
