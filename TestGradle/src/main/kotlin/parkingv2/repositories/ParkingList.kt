package parkingv2.repositories

import parkingv2.interfaces.IParkingRepository
import parkingv2.models.Vehiculo

class ParkingList(private val maxSize: Int = 20): IParkingRepository {

    private val parking = mutableListOf<Vehiculo>()

    override fun estaCompleto(): Boolean {
        return parking.size >= maxSize
    }

    override fun listadoOrdenadoPorMatricula(): Array<Vehiculo> {
        return parking.sortedBy { it.matricula }.toTypedArray()
    }

    override fun listadoOrdenadoPorMatriculacionDesc(): Array<Vehiculo> {
        return parking.sortedByDescending { it.anioMatriculacion }.toTypedArray()
    }

    override fun add(vehiculo: Vehiculo): Vehiculo? {
        if (estaCompleto()) return null
        for (i in parking.indices) {
            if (parking[i].matricula == vehiculo.matricula) return null
        }
        parking.add(vehiculo)
        return vehiculo
    }

    override fun getAll(): Array<Vehiculo> {
        return parking.toTypedArray()
    }

    override fun find(matricula: String): Vehiculo? {
        for (i in parking.indices) {
            if (parking[i].matricula == matricula) return parking[i]
        }
        return null
    }

    override fun update(vehiculo: Vehiculo, oldMatricula: String): Vehiculo? {
        for (i in parking.indices) {
            if (parking[i].matricula == oldMatricula) {
                parking[i] = vehiculo
                return vehiculo
            }
        }
        return null
    }

    override fun delete(matricula: String): Vehiculo? {
        val response = find(matricula) ?: return null
        parking.remove(response)
        return response
    }

    override fun saveAll(array: Array<Vehiculo>) {
        for (i in array) {
            add(i)
        }
    }
}