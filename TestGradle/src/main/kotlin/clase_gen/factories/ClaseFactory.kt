package clase_gen.factories

import clase_gen.enums.Modulo
import clase_gen.models.Alumno
import clase_gen.models.Persona
import clase_gen.models.Profesor

object ClaseFactory {
    fun createClase(size: Int): Array<Persona> {
        require(size > 0){"El tamaño debe ser mayor que 0"}
        require(size < 100){"El tamaño debe ser menor que 100"}
        val list: MutableList<Persona> = mutableListOf()
        for (i in 0 until size){
            list.add(this.createPersona())
        }
        return list.toTypedArray()
    }

    private fun createPersona(): Persona {
        when((0..1).random()){
            0 -> return this.createAlumno()
            else -> return this.createProfesor()
        }
    }

    private fun createProfesor(): Persona {
        return Profesor(nombre = this.createNombre(), edad = this.createEdad(), modulo = Modulo.values().random())
    }

    private fun createAlumno(): Persona {
        return Alumno(nombre = this.createNombre(), edad = this.createEdad(), nota = this.createNota())
    }

    private fun createNota(): Float {
        return (0..10).random().toFloat()
    }

    private fun createEdad(): Int {
        return (18..65).random()
    }

    private fun createNombre(): String {
        val nombres = arrayOf("Pepe", "Juan", "Ana")
        return nombres.random()
    }
}