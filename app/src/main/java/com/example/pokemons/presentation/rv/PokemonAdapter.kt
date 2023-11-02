package com.example.pokemons.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.presentation.PokemonViewHolder
import com.example.pokemons.databinding.ItemPokemonBinding
import com.example.pokemons.domain.Pokemon


class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>(){


    var list = emptyList<Pokemon>()
    lateinit var onClick: (Int) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = list[position]
        holder.bindItem(pokemon)
        holder.itemView.setOnClickListener {
            onClick(pokemon.id)
        }
    }
}