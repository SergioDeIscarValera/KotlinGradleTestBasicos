package parkingv2.models

import parkingv2.enums.Marcas

abstract class Vehiculo(val matricula: String, val marca: Marcas, val modelo: String, val anioMatriculacion: Int){
    override fun equals(other: Any?): Boolean {
        return other is Vehiculo &&
                matricula == other.matricula &&
                marca == other.marca &&
                modelo == other.modelo &&
                anioMatriculacion == other.anioMatriculacion
    }

    override fun hashCode(): Int {
        return matricula.hashCode() + marca.hashCode() + modelo.hashCode() + anioMatriculacion.hashCode()
    }
}