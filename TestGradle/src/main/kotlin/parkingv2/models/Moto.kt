package parkingv2.models

import parkingv2.enums.Marcas

class Moto(matricula: String,
           marca: Marcas,
           modelo: String,
           anioMatriculacion: Int,
           val cilindrada: Int) : Vehiculo(matricula, marca, modelo, anioMatriculacion) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other) && other is Moto && cilindrada == other.cilindrada
    }

    override fun hashCode(): Int {
        return super.hashCode() + cilindrada.hashCode()
    }
}