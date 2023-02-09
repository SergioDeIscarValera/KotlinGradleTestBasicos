package clase_gen.repositories

import clase_gen.models.Persona
import java.util.*

class ClaseRepository: IClaseRepository {
    val personas: Array<Persona?> = arrayOfNulls(25)
    override fun add(persona: Persona): Persona? {
        for (i in personas.indices){
            if (personas[i] == null){
                personas[i] = persona
                return persona
            }
        }
        return null
    }

    private fun findIndex(id: UUID): Int{
        for (i in personas.indices){
            if (personas[i]?.id == id){
                return i
            }
        }
        return -1
    }

    override fun find(id: UUID): Persona? {
        val index = findIndex(id)
        return if (index == -1) null else personas[index]
    }

    override fun update(persona: Persona, oldId: UUID): Persona? {
        val index = findIndex(oldId)
        if (index == -1) return null
        personas[index] = persona
        return personas[index]
    }

    override fun delete(id: UUID): Persona? {
        val index = findIndex(id)
        if (index == -1) return null
        val persona = personas[index]
        personas[index] = null
        return persona
    }

    override fun saveAll(array: Array<Persona>) {
        require(array.size <= personas.size) { throw IllegalArgumentException("El array es mayor al tamaño del repositorio.") }
        for (i in array.indices){
            personas[i] = array[i]
        }
    }

    override fun getAll(): Array<Persona?> {
        return personas
    }

    override fun bubbleSort(filter: (Persona?, Persona?) -> Int): Array<Persona?> {
        for (i in 0 until personas.size - 1) {
            for (j in 0 until personas.size - i - 1) {
                if (filter(personas[j], personas[j + 1]) > 0) {
                    val temp = personas[j]
                    personas[j] = personas[j + 1]
                     personas[j + 1] = temp
                }
            }
        }
        return personas
    }

    fun bubbleSort(): Array<Persona?> {
        return bubbleSort { p1, p2 -> (p1?.edad ?: Int.MAX_VALUE) - (p2?.edad ?: Int.MAX_VALUE) }
    }

}