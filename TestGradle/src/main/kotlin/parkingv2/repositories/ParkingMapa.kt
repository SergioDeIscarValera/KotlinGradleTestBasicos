package parkingv2.repositories

import parkingv2.interfaces.IParkingRepository
import parkingv2.models.Vehiculo

class ParkingMapa (
    private val maxSize: Int = 20): IParkingRepository {

    private val parking = mutableMapOf<String,Vehiculo>()

    override fun estaCompleto(): Boolean {
        return parking.size >= maxSize
    }

    override fun listadoOrdenadoPorMatricula(): Array<Vehiculo> {
        return parking.values.sortedBy { it.matricula }.toTypedArray()
    }

    override fun listadoOrdenadoPorMatriculacionDesc(): Array<Vehiculo> {
        return parking.values.sortedByDescending { it.anioMatriculacion }.toTypedArray()
    }

    override fun add(vehiculo: Vehiculo): Vehiculo? {
        if (estaCompleto()) return null
        if (find(vehiculo.matricula) != null) return null
        // Si no existe lo crea y si ya existe lo actualiza
        parking[vehiculo.matricula] = vehiculo
        return vehiculo
    }

    override fun getAll(): Array<Vehiculo> {
        return parking.values.toTypedArray()
    }

    override fun find(matricula: String): Vehiculo? {
        return parking[matricula]
    }

    override fun update(vehiculo: Vehiculo, oldMatricula: String): Vehiculo? {
        if (find(oldMatricula) == null) return null
        parking[oldMatricula] = vehiculo
        return vehiculo
    }

    override fun delete(matricula: String): Vehiculo? {
        return parking.remove(matricula)
    }

    override fun saveAll(array: Array<Vehiculo>) {
        for (i in array){
            add(i)
        }
    }
}