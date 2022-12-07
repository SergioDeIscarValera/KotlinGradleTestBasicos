package PokemonMaker

import PokemonMaker.enums.Tipo
import PokemonMaker.models.Características
import PokemonMaker.models.Movimiento
import PokemonMaker.models.Pokemon

fun main(){
    val equipoPlayer = Array<Pokemon?>(6){ null }
    val equipoEnemigo = Array<Pokemon?>((1..6).random()){ rdnPokemon() }

    if (inputBoolean("¿Quieres que tu equipo sea aleatorio?")){
        val rdn = (0..5).random()
        for (i in 0..rdn){
            equipoPlayer[i] = rdnPokemon()
        }
    }
    mostrarEquipo("Jugador", equipoPlayer)
    mostrarEquipo("Enemigo", equipoEnemigo)
}

fun mostrarEquipo(nombre: String, equipo: Array<Pokemon?>) {
    println("Equipo de $nombre")
    for (i in equipo){
        if (i != null){
            println(i)
            println()
        }
    }
    println()
}

private fun rdnPokemon(): Pokemon{
    val tipo = Tipo.values().random()
    val subTipo = Tipo.values().random()
    val características = Características(
        PS = (10u..255u).random(),
        ataque = (10u..255u).random(),
        defensa = (10u..255u).random(),
        ataEspecial = (10u..255u).random(),
        defEspecial = (10u..255u).random(),
        velocidad = (10u..255u).random())
    val pokemon = Pokemon("$tipo-$subTipo:${características.PS}", tipo, subTipo, características)
    for (i in 0..(0..3).random()){
        pokemon.addMovimiento(rdnMovimiento())
    }
    return pokemon
}

private fun rdnMovimiento(): Movimiento{
    val tipo = Tipo.values().random()
    val poder = (10u..80u).random()
    return Movimiento("$tipo:$poder", tipo, poder, (5u..30u).random(), "RDN")
}

// region Input
private fun inputBoolean(message: String): Boolean{
    println("$message (S/N):")
    var response: String
    do {
        response = readln().lowercase()
    }while (response != "s" && response != "n")
    return response == "s"
}
// endregion