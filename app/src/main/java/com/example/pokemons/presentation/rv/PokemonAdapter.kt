package com.example.pokemons.presentation.rv

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.presentation.PokemonViewHolder
import com.example.pokemons.databinding.ItemPokemonBinding
import com.example.pokemons.domain.Pokemon
import com.example.pokemons.presentation.MakePokemonParcelable


class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>(){

    private var pokemonList: List<Pokemon> = emptyList()
    lateinit var onClick: (Parcelable) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = pokemonList.size
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bindItem(pokemon)
        holder.itemView.setOnClickListener {
            onClick(MakePokemonParcelable(pokemon))
        }
    }
    fun setData(newDataList: List<Pokemon>) {
        pokemonList = newDataList
        notifyDataSetChanged()
    }
}