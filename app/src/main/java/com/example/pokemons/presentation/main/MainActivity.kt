package com.example.pokemons.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.pokemons.R
import com.example.pokemons.presentation.rv.PokemonAdapter
import com.example.pokemons.di.MyApplication.Companion.dependencyContainer
import com.example.pokemons.presentation.details.PokemonDetailsFragment
import com.example.pokemons.presentation.rv.RvFragment

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.commit {
            replace<RvFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("name")
        }

        adapter = dependencyContainer.adapter
        adapter.onClick = { openDetailFragment(it)}
    }



    private fun openDetailFragment(pokemon: Parcelable){
        val bundle = Bundle()
        bundle.putParcelable("pokemonData",pokemon)

        val fragment = PokemonDetailsFragment()
        fragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment, fragment.toString())
            .addToBackStack("myStack")
        fragmentTransaction.commit()
    }
}