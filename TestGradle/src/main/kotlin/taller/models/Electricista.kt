package taller.models

open class Electricista(nombre: String, experiencia: Int, horasDiarias: Int): Trabajador(nombre, experiencia, horasDiarias) {
    override fun comer() {
        println("El trabajador electricista $nombre come.")
    }
}