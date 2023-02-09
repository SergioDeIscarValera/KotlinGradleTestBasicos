package clase_gen.models

import java.util.UUID

class Alumno(nombre: String, edad: Int, val nota: Float): Persona(nombre = nombre, edad = edad){
    constructor(id: UUID, nombre: String, edad: Int, nota: Float): this(nombre, edad, nota){
        super.id = id
    }
    override fun toString(): String {
        return "Soy el alumno $nombre, tengo $edad y tengo un $nota"
    }
}