package PokemonMaker.models

data class Equipo (val nombreEntrenador: String){
    private val pokemons = Array<Pokemon?>(6){ null }

    fun getPokemons(): Array<Pokemon?>{
        return pokemons
    }

    fun getPokemon(index: Int): Pokemon?{
        return pokemons[index]
    }

    fun addPokemon(pokemon: Pokemon): Pokemon?{
        for (i in pokemons.indices){
            if (pokemons[i] == null){
                pokemons[i] = pokemon
                return pokemon
            }
        }
        return null
    }

    fun updatePokemon(index: Int, pokemon: Pokemon): Pokemon?{
        if (getPokemon(index) == null) return null
        pokemons[index] = pokemon
        return pokemon
    }

    fun deletePokemon(index: Int): Boolean{
        if (getPokemon(index) == null) return false
        pokemons[index] = null
        return true
    }

    // region Movimiento
    fun getMovimientoPokemon(indexPokemon: Int, indexMovimiento: Int): Movimiento? {
        if (getPokemon(indexPokemon) == null) return null
        return pokemons[indexPokemon]?.getMovimiento(indexMovimiento)
    }

    fun addMovimientoPokemon(indexPokemon: Int, movimiento: Movimiento): Movimiento? {
        if (getPokemon(indexPokemon) == null) return null
        return pokemons[indexPokemon]?.addMovimiento(movimiento)
    }

    fun updateMovimientoPokemon(indexPokemon: Int, indexMovimiento: Int, movimiento: Movimiento): Movimiento?{
        if (getPokemon(indexPokemon) == null) return null
        return pokemons[indexPokemon]?.updateMovimiento(indexMovimiento, movimiento)
    }

    fun deleteMovimientoPokemon(indexPokemon: Int, indexMovimiento: Int): Boolean{
        if (getPokemon(indexPokemon) == null) return false
        return pokemons[indexPokemon]?.deleteMovimiento(indexMovimiento) ?: false
    }
    // endregion
}