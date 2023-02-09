package clase_gen.repositories

import clase_gen.models.Persona
import java.util.*

interface IClaseRepository: ICrudRepository<Persona, UUID> {
    fun bubbleSort(filter: (Persona?, Persona?) -> Int): Array<Persona?>
}