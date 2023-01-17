package taller.models

class Chapista(nombre: String, experiencia: Int, horasDiarias: Int): Trabajador(nombre, experiencia, horasDiarias) {
    override fun comer() {
        println("El trabajador chapista $nombre come.")
    }

    fun arreglarChapa(){
        println("El chapista $nombre esta arreglando la chapa.")
    }
}