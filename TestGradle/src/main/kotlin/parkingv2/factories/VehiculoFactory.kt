package parkingv2.factories

import parkingv2.enums.Marcas
import parkingv2.models.Camioneta
import parkingv2.models.Coche
import parkingv2.models.Moto
import parkingv2.models.Vehiculo

object VehiculoFactory {
    fun getRdnVehiculo(): Vehiculo {
        return when((1..3).random()){
            1 -> getRdnCoche()
            2 -> getRdnMoto()
            3 -> getRdnCamioneta()
            else -> throw Exception("Error al crear vehiculo")
        }
    }

    fun getRdnVehiculoList(size: Int): List<Vehiculo> {
        val list = mutableListOf<Vehiculo>()
        for (i in 1..size){
            list.add(getRdnVehiculo())
        }
        return list.toList()
    }

    private fun getRdnCoche(): Coche {
        return Coche(getRdnMatricula(), Marcas.values().random(), getRdnModelo(), getRdnAnio(), (2..7).random())
    }

    private fun getRdnMoto(): Moto {
        return Moto(getRdnMatricula(), Marcas.values().random(), getRdnModelo(), getRdnAnio(), (50..200).random())
    }

    private fun getRdnCamioneta(): Camioneta {
        return Camioneta(getRdnMatricula(), Marcas.values().random(), getRdnModelo(), getRdnAnio(), (1000..5000).random())
    }

    private fun getRdnMatricula(): String{
        val number = (1000..9999).random()
        val letters = ('A'..'Z').shuffled().take(3).joinToString("")
        return "$number$letters"
    }

    private fun getRdnModelo(): String {
        return when((1..3).random()){
            1 -> "Fiesta"
            2 -> "Focus"
            3 -> "Mustang"
            else -> throw Exception("Error al crear vehiculo")
        }
    }

    private fun getRdnAnio(): Int {
        return (1900..2023).random()
    }
}