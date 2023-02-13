package parkingv2.controllers

import parkingv2.interfaces.IParkingController
import parkingv2.interfaces.IParkingRepository
import parkingv2.models.Vehiculo

class ParkingController(
    private val repository: IParkingRepository
): IParkingController {
    override fun estaCompleto(): Boolean {
        return repository.estaCompleto()
    }

    override fun listadoOrdenadoPorMatricula(): Array<Vehiculo> {
        return repository.listadoOrdenadoPorMatricula()
    }

    override fun listadoOrdenadoPorMatriculacionDesc(): Array<Vehiculo> {
        return repository.listadoOrdenadoPorMatriculacionDesc()
    }

    override fun add(vehiculo: Vehiculo): Vehiculo {
        require(!estaCompleto()) { "Parking esta lleno" }
        require(repository.find(vehiculo.matricula) == null) { "El vehiculo ya esta en el parking" }
        require(checkVehiculo(vehiculo)) { "Matricula o año de matriculacion incorrectos" }
        return repository.add(vehiculo)!!
    }

    override fun getAll(): Array<Vehiculo> {
        return repository.getAll()
    }

    override fun find(matricula: String): Vehiculo? {
        require(checkMatricula(matricula)) { "Matricula incorrecta" }
        return repository.find(matricula)
    }

    override fun update(vehiculo: Vehiculo, oldMatricula: String): Vehiculo {
        require(checkVehiculo(vehiculo)){ "Matricula o año de matriculacion incorrectos" }
        require(checkMatricula(oldMatricula)) { "Matricula incorrecta" }
        require(repository.find(oldMatricula) != null) { "El vehiculo no esta en el parking" }
        return repository.update(vehiculo, oldMatricula)!!
    }

    override fun delete(matricula: String): Vehiculo {
        require(checkMatricula(matricula)) { "Matricula incorrecta" }
        require(repository.find(matricula) != null) { "El vehiculo no esta en el parking" }
        return repository.delete(matricula)!!
    }

    private fun checkVehiculo(vehiculo: Vehiculo): Boolean {
        return checkMatricula(vehiculo.matricula) && vehiculo.anioMatriculacion in 1900..2023
    }

    private fun checkMatricula(matricula: String): Boolean{
        return Regex("^[0-9]{4}[A-Z]{3}$").matches(matricula)
    }
}