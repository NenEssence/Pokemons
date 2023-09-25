package com.example.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.Presentation.PokemonViewHolder
import com.example.pokemons.databinding.ItemPokemonBinding


class PokemonAdapter(var pokemonList: List<Pokemon>): RecyclerView.Adapter<PokemonViewHolder>() {

    lateinit var onClick: (Int) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindItem(pokemon)
        holder.itemView.setOnClickListener {
            onClick(position)

        }
    }
}