package Aparcamiento.models

import Aparcamiento.enums.TipoVehiculo

data class Vehiculo(val conductor: Conductor, val matricula: String, val anio: Int, val tipo: TipoVehiculo){
    val id = Vehiculo.nextId()
    companion object{
        var contador = 0
        private fun nextId(): Int {
            return contador++
        }
    }

    override fun toString(): String {
        return """"vehiculo":{ "conductor":${conductor.toJsonString()}, "matricula":"$matricula", "añó":$anio, "tipo":"$tipo" } """
    }
}
