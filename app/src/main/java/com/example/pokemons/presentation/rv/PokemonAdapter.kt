package com.example.pokemons.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.data.PokemonList.pokemonList
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.presentation.PokemonViewHolder
import com.example.pokemons.databinding.ItemPokemonBinding


class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>() {

    lateinit var onClick: (Pokemon) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindItem(pokemon)
        holder.itemView.setOnClickListener {
            onClick(pokemon)

        }
    }
}