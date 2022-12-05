package Objetos.FootBall

import Objetos.FootBall.enums.TipoDenomination
import Objetos.FootBall.models.Entrenador
import Objetos.FootBall.models.Jugador
import Objetos.FootBall.models.Selection

public val COUNT_JUGADORES = 30

fun main(){
    val selection = Selection("", Array(COUNT_JUGADORES){null}, Entrenador("", 0))
    selection.pais = inputString("Introduce el nombre del pais de la selección:")
    selection.entrenador = Entrenador(inputString("Introduce el nombre del entrenador:"), inputNumber("Introduce los años de experiencia que tiene el entrenador:", 0..100))
    loopMain@do {
        when(menu()){
            0 -> mostrarSeleccion(selection)
            1 -> {
                if (selection.getJugadorById(inputNumber("Introduce el id del jugador a buscar:")) != null){
                    println(selection.getJugadorById(inputNumber("Introduce el id del jugador a buscar:")))
                }else println("No se ha encontrado")
            }
            2 -> {
                for (i in selection.getJugadoresByDorsal(inputNumber("Introduce la dorsal a buscar:", 1..11))){
                    if (i != null) println(i)
                }
            }
            3 -> {
                for (i in selection.getJugadoresByDenomination(inputDenomination("Introduce la denominación a buscar:"))){
                    if (i != null) println(i)
                }
            }
            4 -> {
                if (selection.addJugador(inputJugadorValues("Introduce los datos para crear un nuevo jugador:")) != null){
                    println("El jugador se ha añadido correctamente")
                }else println("El jugador no se ha añadido correctamente")
            }
            5 -> {
                if (selection.updateJugadorById(
                        inputNumber("Introduce el id del jugador a actualizar:"),
                        inputJugadorValues("Introduce los datos para actualizar un nuevo jugador:")
                    ) != null){
                    println("Se ha actualizado correctamente")
                } else println("No se ha actualizado correctamente")
            }
            6 -> { if (selection.deleteJugadorById(inputNumber("Introduce el id del jugador a borrar:"))){
                println("Se ha borrado correctamente")
            } else println("No se ha borrado correctamente")}
            else -> break@loopMain
        }
        Thread.sleep(1500)
    }while (countJugadores(selection) < COUNT_JUGADORES)

    mostrarSeleccion(selection)
}

// region Output
private fun mostrarSeleccion(selection: Selection) {
    clearConsole()
    println("El pais de la selección es ${selection.pais}.")
    println("El entrenador de la selección es ${selection.entrenador.nombre} y tiene ${selection.entrenador.experience} años de experiencia.")
    for (i in selection.jugadores){
        if (i!=null){
            println(i)
            Thread.sleep(500)
        }
    }
    Thread.sleep(1000)
}
// endregion

// region Menus
private fun menu(): Int {
    clearConsole()
    // region Println
    println("0 -> Mostrar Selección")
    println("1 -> Obtener Jugador/es")
    println("2 -> Añadir jugador")
    println("3 -> Actualizar jugador")
    println("4 -> Eliminar jugador")
    println("5 -> Salir")
    // endregion
    var response: Int
    do {
        response = readln().toIntOrNull() ?: -1
        if (response !in 0..5) println("Error: introduzca un valor valido.")
    }while (response !in 0..5)
    return when(response){
        0 -> 0
        1 -> menuGetJugador()
        2 -> 4
        3 -> 5
        4 -> 6
        else -> 7
    }
}

private fun menuGetJugador(): Int {
    clearConsole()
    // region Println
    println("0 -> Obtener Jugador por id")
    println("1 -> Obtener Jugadores por dorsal")
    println("2 -> Obtener Jugadores por denominación")
    // endregion
    var response: Int
    do {
        response = readln().toIntOrNull() ?: -1
        if (response !in 0..2) println("Error: introduzca un valor valido.")
    }while (response !in 0..2)
    return when(response){
        0 -> 1
        1 -> 2
        else -> 3
    }
}
// endregion

// region Input
private fun inputNumber(message: String, range: IntRange = -999..999): Int{
    clearConsole()
    println(message)
    var response: Int?
    do {
        response = readln().toIntOrNull()
        if (response !in range) println("Error: Introduce un número entre ${range.first}-${range.last}.")
    }while (response !in range)
    return response ?: -1
}

private fun inputDenomination(message: String): TipoDenomination{
    clearConsole()
    println(message)
    println(TipoDenomination.values().joinToString())
    var responseString: String
    do {
        responseString = readln().uppercase()
        if (!stringInDenomination(responseString)) println("Error: Introduce uno de estos valores, ${TipoDenomination.values().joinToString()}:")
    }while (!stringInDenomination(responseString))
    return TipoDenomination.valueOf(responseString)
}

private fun stringInDenomination(s: String): Boolean{
    for (i in TipoDenomination.values()){
        if (i.name == s){
            return true
        }
    }
    return false
}

private fun inputJugadorValues(message: String): Jugador{
    clearConsole()
    println(message)
    return Jugador(
        inputString("Introduce el nombre del jugador:"),
        inputNumber("Introduce la dorsal del jugador:", 1..11),
        inputDenomination("Introduce la denominación del jugador:")
    )
}

fun inputString(message: String): String {
    clearConsole()
    println(message)
    return readln()
}

// endregion

fun countJugadores(selection: Selection): Int {
    var count = 0
    for (i in selection.jugadores){
        if (i != null) count++
    }
    return count
}

private fun clearConsole(count: Int = 30){
    for (i in 0..count){
        println()
    }
}