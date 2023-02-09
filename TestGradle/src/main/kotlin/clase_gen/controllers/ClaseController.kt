package clase_gen.controllers

import clase_gen.exceptions.ClaseExceptions
import clase_gen.factories.ClaseFactory
import clase_gen.models.Persona
import clase_gen.repositories.ClaseRepository
import java.util.UUID

class ClaseController(
    private val claseRepository: ClaseRepository
): IClaseController {

    fun loadData(){
        val array = ClaseFactory.createClase((5..15).random())
        for (i in array.indices){
            claseRepository.add(array[i])
        }
    }

    override fun add(t: Persona): Persona {
        require(t.nombre.isNotEmpty() && t.edad > 0) { throw ClaseExceptions.ClaseArgumentException(
            "No se pudo agregar, la clase no cumple los parámetro.")
        }
        return claseRepository.add(t) ?: throw ClaseExceptions.ClaseInvalidException("No se pudo agregar.")
    }

    override fun getAll(): Array<Persona?> {
        return claseRepository.getAll()
    }

    override fun delete(id: UUID): Persona {
        return claseRepository.delete(id) ?: throw ClaseExceptions.ClaseInvalidException("No se pudo eliminar.")
    }

    override fun update(t: Persona, oldId: UUID): Persona {
        require(t.nombre.isNotEmpty() && t.edad > 0) { throw ClaseExceptions.ClaseArgumentException(
            "No se pudo actualizar, la clase no cumple los parámetro.")
        }
        return claseRepository.update(t, oldId) ?: throw ClaseExceptions.ClaseInvalidException("No se pudo actualizar.")
    }

    override fun find(id: UUID): Persona? {
        return claseRepository.find(id)
    }

    override fun bubbleSort(): Array<Persona?> {
        return claseRepository.bubbleSort()
    }

}