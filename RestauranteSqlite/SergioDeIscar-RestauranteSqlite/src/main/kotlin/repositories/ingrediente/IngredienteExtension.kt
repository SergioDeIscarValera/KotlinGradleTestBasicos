package repositories.ingrediente

import models.Ingrediente
import repositories.CrudRepository
import java.util.UUID

interface IngredienteExtension: CrudRepository<Ingrediente, UUID> {
    fun findByLong(id: Long): Ingrediente?
    fun findByNombre(nombre: String): List<Ingrediente>
    fun findByDisponible(disponible: Boolean): List<Ingrediente>
    fun deleteByLong(id: Long): Ingrediente?
    fun deleteAll(): Boolean
}