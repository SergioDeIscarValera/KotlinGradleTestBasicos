package parkingv2

import parkingv2.controllers.ParkingController
import parkingv2.repositories.ParkingConjunto
import parkingv2.repositories.ParkingList
import parkingv2.repositories.ParkingMapa

fun main(){
    val controllerList = ParkingController(ParkingList())
    val controllerConjunto = ParkingController(ParkingConjunto())
    val controllerMapa = ParkingController(ParkingMapa())
}