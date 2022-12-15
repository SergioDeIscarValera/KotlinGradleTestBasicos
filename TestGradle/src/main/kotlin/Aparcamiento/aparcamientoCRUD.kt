package Aparcamiento

import Aparcamiento.enums.TipoVehiculo
import Aparcamiento.models.Aparcamiento
import Aparcamiento.models.Conductor
import Aparcamiento.models.Vehiculo
import Objetos.FootBall.clearConsole
import Objetos.FootBall.inputNumber
import Objetos.FootBall.inputString

fun main(){
    val aparcamiento = Aparcamiento()
    do {
        when(menu()){
            1 -> println("Hay un total de ${aparcamiento.getVehiculoCount()} vehículos aparcados")
            2 -> outVehiculo(
                aparcamiento.getVehiculo(inputMatricula("Introduce la matricula del coche a buscar:")),
                "No hay ningún coche con esa matricula."
            )
            3 -> outVehiculo(
                aparcamiento.aparcarVehiculo(inputVehiculo("Introduce los datos de vehiculo a aparcar:")),
                "El aparcamiento esta lleno."
            )
            4 -> {
                if (aparcamiento.sacarVehiculo(inputMatricula("Introduce la matricula del coche a sacar:"))) println("El vehiculo ha sido retirado exitosamente.")
                else println("El vehiculo no se ha encontrado.")
            }
            5 -> println("El conductor tiene un total de ${aparcamiento.getVehiculoConductor(inputConductor("Introduce los datos del conductor a buscar entre los coches:"))} coches aparcados")
            6 -> {
                val sortArray = aparcamiento.getVehiculoSort()
                for (i in sortArray.indices){
                    println("${i+1}->\t${sortArray[i]}")
                }
            }
            7 -> println("En el momento actual el balance es de ${aparcamiento.getBalance()} €")
            else -> break
        }
        Thread.sleep(1500)
    }while (true)
}
// region Output

fun outVehiculo(vehiculo: Vehiculo?, errorMessage: String){
    if (vehiculo != null) println(vehiculo)
    else println(errorMessage)
}

// endregion

// region Input

fun inputVehiculo(s: String): Vehiculo {
    clearConsole()
    println(s)
    return Vehiculo(
        inputConductor("Introduce los datos del conductor:"),
        inputMatricula("Introduce la matricula del nuevo coche:"),
        inputNumber("Introduce el año de fabricación:", 2000..2022),
        inputTipoVehiculo("Introduce el tipo del vehiculo:")
    )
}

fun inputTipoVehiculo(message: String): TipoVehiculo {
    clearConsole()
    println(message)
    println(TipoVehiculo.values().joinToString())
    var responseString: String
    do {
        responseString = readln().uppercase()
        if (!stringInTipo(responseString)) println("Error: Introduce uno de estos valores, ${TipoVehiculo.values().joinToString()}:")
    }while (!stringInTipo(responseString))
    return TipoVehiculo.valueOf(responseString)
}

private fun stringInTipo(s: String): Boolean{
    for (i in TipoVehiculo.values()){
        if (i.name == s){
            return true
        }
    }
    return false
}

fun inputConductor(message: String): Conductor {
    clearConsole()
    println(message)
    return Conductor(
        inputString("Introduce el nombre del conductor:"),
        inputDNI("Introduce el DNI del conductor:")
    )
}

fun inputDNI(message: String): String {
    return inputStringRegex(message, "Introduce un DNI valido", Regex("^\\d{8}[A-Z]\$"))
}

fun inputMatricula(message: String): String {
    return inputStringRegex(message, "Introduce una matricula valida", Regex("^\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}\$"))
}

fun inputStringRegex(message: String, errorMessage: String, regex: Regex): String {
    clearConsole()
    println(message)
    var response: String
    do {
        response = readln()
        if (response.matches(regex)) break
        else println(errorMessage)
    }while (true)
    return response
}

// endregion

// region Menu
fun menu(): Int{
    return inputNumber("¿Qué quieres hacer?\n" +
            "1->\tCount Vehicles\n" +
            "2->\tBuscar vehiculo por matricula\n" +
            "3->\tAparcar nuevo coche\n" +
            "4->\tSacar coche\n" +
            "5->\tCuantos coches tiene un conductor\n" +
            "6->\tCoches ordenados por antigüedad\n" +
            "7->\tRecaudación actual\n" +
            "8->\tSalir", 1..8)
}
// endregion