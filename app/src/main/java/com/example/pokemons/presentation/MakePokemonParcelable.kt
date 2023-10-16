package com.example.pokemons.presentation
import android.os.Parcel
import android.os.Parcelable
import com.example.pokemons.domain.Pokemon

fun Parcel.writePokemon(pokemon: Pokemon) {
    writeInt(pokemon.id)
    writeString(pokemon.name)
    writeString(pokemon.type)
    writeInt(pokemon.imageFile)
    writeDouble(pokemon.hight)
    writeDouble(pokemon.weight)
}

fun Parcel.readPokemon(): Pokemon {

    val id = readInt()
    val name = readString() ?: ""
    val type = readString() ?: ""
    val imageFile = readInt()
    val hight = readDouble()
    val weight = readDouble()

    return Pokemon(id, name, type,imageFile,hight,weight)
}

class MakePokemonParcelable(val pokemon: Pokemon) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readPokemon())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writePokemon(pokemon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MakePokemonParcelable> {
        override fun createFromParcel(parcel: Parcel): MakePokemonParcelable {
            return MakePokemonParcelable(parcel)
        }

        override fun newArray(size: Int): Array<MakePokemonParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
