package clase_gen.models

import clase_gen.enums.Modulo
import java.util.*

class Profesor(nombre: String, edad: Int, val modulo: Modulo): Persona(nombre = nombre, edad = edad){
    constructor(id: UUID, nombre: String, edad: Int, modulo: Modulo): this(nombre, edad, modulo){
        super.id = id
    }
    override fun toString(): String {
        return "Soy el profesor $nombre, tengo $edad años e imparto $modulo"
    }
}