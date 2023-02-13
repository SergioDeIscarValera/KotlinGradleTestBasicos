package parkingv2.repositories

import parkingv2.interfaces.IParkingRepository
import parkingv2.models.Vehiculo
import java.util.TreeSet

class ParkingConjunto(private val maxSize: Int = 20): IParkingRepository {

    private val parking = TreeSet<Vehiculo> { v1, v2 -> v1.matricula.compareTo(v2.matricula)}

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
        // Al ser un conjunto, si ya existe no lo añade
        parking.add(vehiculo)
        return if (find(vehiculo.matricula) != null) vehiculo else null
    }

    override fun getAll(): Array<Vehiculo> {
        return parking.toTypedArray()
    }

    override fun find(matricula: String): Vehiculo? {
        return parking.find { it.matricula == matricula }
    }

    override fun update(vehiculo: Vehiculo, oldMatricula: String): Vehiculo? {
        parking.find { it.matricula == oldMatricula }?.let {
            parking.remove(it)
            parking.add(vehiculo)
            return vehiculo
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