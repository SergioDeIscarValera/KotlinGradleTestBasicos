package PokemonMaker

import Objetos.FootBall.clearConsole
import Objetos.FootBall.inputNumber
import Objetos.FootBall.inputString
import PokemonMaker.enums.Tipo
import PokemonMaker.models.Características
import PokemonMaker.models.Equipo
import PokemonMaker.models.Movimiento
import PokemonMaker.models.Pokemon

fun main(){
    val equipo = Equipo(inputString("Introduce tu nombre de entrenador:"))
    do {
        when(Menu()){
            1 -> mostrarEquipo(equipo.nombreEntrenador, equipo.getPokemons())
            2 -> {
                val newPokemon = equipo.addPokemon(inputPokemon("Introduce los valores para el nuevo pokemon:"))
                if (newPokemon == null) println("Error: fallo al añadir un pokemon, puede que su equipo este lleno.")
                else println("Nuevo pokemon en el equipo:\n\t$newPokemon")
            }
            3 -> {
                val newPokemon = equipo.updatePokemon(inputNumber("Introduce la posición en el equipo del pokemon a actualizar:", 1..6) -1, inputPokemon("Introduce los nuevos valores para el pokemon:"))
                if (newPokemon == null) println("Error: fallo al actualizar compruebe que la posición introducida hay un pokemon.")
                else println("Pokemon actualizado:\n\t$newPokemon")
            }
            4 -> {
                if (!equipo.deletePokemon(inputNumber("Introduce la posición en el equipo del pokemon a eliminar:", 1..6) -1)) println("Error: fallo al eliminar compruebe que la posición introducida hay un pokemon.")
                else println("Pokemon eliminado correctamente.")
            }
            5 -> {
                val newMovimiento = equipo.getMovimientoPokemon(inputNumber("Introduce la posición en el equipo del pokemon:", 1..6) -1, inputNumber("Introduce la posición del movimiento:", 1..4) -1)
                if (newMovimiento == null) println("Error: No se ha podido acceder o al pokemon o al movimiento")
                else println(newMovimiento)
            }
            6 -> {
                val newMovimiento = equipo.addMovimientoPokemon(inputNumber("Introduce la posición en el equipo del pokemon:", 1..6) -1, inputMovimiento("Introduce el nuevo movimiento:"))
                if (newMovimiento == null) println("Error: No se ha podido añadir el movimiento")
                else println(newMovimiento)
            }
            7 ->{
                val newMovimiento = equipo.updateMovimientoPokemon(inputNumber("Introduce la posición en el equipo del pokemon:", 1..6) -1, inputNumber("Introduce la posición del movimiento a actualizar:", 1..4) -1, inputMovimiento("Introduce el nuevo movimiento:"))
                if (newMovimiento == null) println("Error: no se ha podido actualizar el movimiento")
                else println(newMovimiento)
            }
            8 ->{
                if (!equipo.deleteMovimientoPokemon(inputNumber("Introduce la posición en el equipo del pokemon:", 1..6) -1, inputNumber("Introduce la posición del movimiento a eliminar:", 1..4) -1)) println("Error: No se ha podido eliminar el movimiento")
                else println("Movimiento eliminado correctamente.")
            }
            else -> break
        }
        Thread.sleep(1500)
    }while (true)
    mostrarEquipo(equipo.nombreEntrenador, equipo.getPokemons())
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
    Thread.sleep(1500)
}

// region Menus
private fun Menu(): Int{
    return inputNumber("¿Qué quieres hacer?\n1 -> mostrar equipo\n2 -> añadir pokemon\n3 -> actualizar pokemon\n4 -> eliminar pokemon\n5 -> mostrar movimiento de un pokemon\n6 -> añadir movimiento a un pokemon\n7 -> actualizar movimiento a un pokemon\n8 -> eliminar movimiento de un pokemon\n9 -> salir",1..9)
}
// endregion

// region Random
private fun rdnPokemon(): Pokemon{
    val tipo = Tipo.values().random()
    val subTipo = Tipo.values().random()
    val características = rdnCaracteristicas()
    val pokemon = Pokemon("$tipo-$subTipo:${características.PS}", tipo, subTipo, características)
    for (i in 0..(0..3).random()){
        pokemon.addMovimiento(rdnMovimiento())
    }
    return pokemon
}

fun rdnCaracteristicas(): Características {
    return Características(
        PS = (10u..255u).random(),
        ataque = (10u..255u).random(),
        defensa = (10u..255u).random(),
        ataEspecial = (10u..255u).random(),
        defEspecial = (10u..255u).random(),
        velocidad = (10u..255u).random())
}

private fun rdnMovimiento(): Movimiento{
    val tipo = Tipo.values().random()
    val poder = (10u..80u).random()
    return Movimiento("$tipo:$poder", tipo, poder, (5u..30u).random(), "RDN")
}
// endregion

// region Input
private fun inputBoolean(message: String): Boolean{
    println("$message (S/N):")
    var response: String
    do {
        response = readln().lowercase()
    }while (response != "s" && response != "n")
    return response == "s"
}

// Pokemon
private fun inputPokemon(message: String): Pokemon{
    return Pokemon(inputString("Introduce el nombre del pokemon:"), inputTipo("Introduce el tipo del pokemon:"), inputTipo("Introduce el subtipo del pokemon:"), rdnCaracteristicas())
}

// Movimiento

private fun inputMovimiento(message: String): Movimiento{
    return Movimiento(inputString("Introduce el nombre del movimiento:"), inputTipo("Introduce el tipo del movimiento:"), (10u..80u).random(), (5u..30u).random(), inputString("Introduce la descripción de los efectos de este movimiento:"))
}

// Tipo
private fun inputTipo(message: String):Tipo{
    //clearConsole()
    println(message)
    println(Tipo.values().joinToString())
    var responseString: String
    do {
        responseString = readln().uppercase()
        if (!stringInTipo(responseString)) println("Error: Introduce uno de estos valores, ${Tipo.values().joinToString()}:")
    }while (!stringInTipo(responseString))
    return Tipo.valueOf(responseString)
}
private fun stringInTipo(s: String): Boolean{
    for (i in Tipo.values()){
        if (i.name == s){
            return true
        }
    }
    return false
}
// endregion