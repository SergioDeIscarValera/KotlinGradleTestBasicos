package parkingv2.models

import parkingv2.enums.Marcas

class Camioneta(matricula: String,
                marca: Marcas,
                modelo: String,
                anioMatriculacion: Int,
                val capacidad: Int) : Vehiculo(matricula, marca, modelo, anioMatriculacion) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) && other is Camioneta && capacidad == other.capacidad
    }

    override fun hashCode(): Int {
        return super.hashCode() + capacidad.hashCode()
    }
}