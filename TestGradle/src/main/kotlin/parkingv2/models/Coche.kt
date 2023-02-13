package parkingv2.models

import parkingv2.enums.Marcas

class Coche(matricula: String,
            marca: Marcas,
            modelo: String,
            anioMatriculacion: Int,
            val plazas: Int) : Vehiculo(matricula, marca, modelo, anioMatriculacion) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) && other is Coche && plazas == other.plazas
    }

    override fun hashCode(): Int {
        return super.hashCode() + plazas.hashCode()
    }
}