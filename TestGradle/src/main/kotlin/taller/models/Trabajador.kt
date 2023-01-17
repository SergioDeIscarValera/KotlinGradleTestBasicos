package taller.models

abstract class Trabajador(nombre: String, experiencia: Int, val horasDiarias: Int): Persona(nombre, experiencia) {
    fun descansar(){
        println("El trabajador $nombre descansa.")
    }
    override fun saludar() {
        println("Trabajador $nombre saluda con respeto.")
    }
    abstract fun comer()
}