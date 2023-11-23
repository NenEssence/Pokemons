package com.example.pokemons.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemons.databinding.ItemPokemonBinding
import com.example.pokemons.domain.entities.Pokemon
import com.example.pokemons.presentation.PokemonViewHolder

class PokemonAdapter() : RecyclerView.Adapter<PokemonViewHolder>() {
    var list = emptyList<Pokemon>()
    lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = list[position]
        Glide.with(holder.itemView.context).load(pokemon.imageFile).into(holder.pokemonImageView)
        holder.bindItem(pokemon)
        holder.itemView.setOnClickListener { onClick(pokemon.id) }
    }
}
