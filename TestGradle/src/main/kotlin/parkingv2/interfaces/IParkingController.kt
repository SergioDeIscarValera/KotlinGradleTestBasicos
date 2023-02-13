package parkingv2.interfaces

import parkingv2.models.Vehiculo

interface IParkingController: IController<Vehiculo, String>{
    fun estaCompleto(): Boolean
    fun listadoOrdenadoPorMatricula(): Array<Vehiculo>
    fun listadoOrdenadoPorMatriculacionDesc(): Array<Vehiculo>
}