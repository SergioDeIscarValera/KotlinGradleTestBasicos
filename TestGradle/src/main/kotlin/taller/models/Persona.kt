package taller.models

abstract class Persona(val nombre: String, var experiencia: Int) {
    val id = contador++
    companion object{
        var contador = 0
    }

    abstract fun saludar()
}