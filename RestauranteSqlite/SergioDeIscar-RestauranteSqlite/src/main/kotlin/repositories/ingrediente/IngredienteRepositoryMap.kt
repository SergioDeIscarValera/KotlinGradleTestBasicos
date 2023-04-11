package repositories.ingrediente

import config.AppConfig
import models.Ingrediente
import services.storage.ingrediente.IngredienteStorageService
import java.sql.DriverManager
import java.time.LocalDateTime
import java.util.*

class IngredienteRepositoryMap(
    private val storageService: IngredienteStorageService
): IngredienteRepository{
    private val ingredientes = mutableMapOf<UUID, Ingrediente>()

    override fun findByLong(id: Long): Ingrediente? {
        upgrade()
        return ingredientes.values.find { it.id == id }
    }

    override fun findByNombre(nombre: String): List<Ingrediente> {
        upgrade()
        return ingredientes.values.filter { it.nombre == nombre }
    }

    override fun findByDisponible(disponible: Boolean): List<Ingrediente> {
        upgrade()
        return ingredientes.values.filter { it.disponible == disponible }
    }

    override fun deleteByLong(id: Long): Ingrediente? {
        return deleteById(findByLong(id)?.uuid ?: return null)
    }

    override fun deleteAll(): Boolean {
        ingredientes.clear()
        downgrade()
        return ingredientes.isEmpty()
    }

    override fun getAll(): List<Ingrediente> {
        upgrade()
        return ingredientes.values.toList()
    }

    override fun getById(id: UUID): Ingrediente? {
        upgrade()
        return ingredientes[id]
    }

    override fun save(element: Ingrediente, storage: Boolean): Ingrediente {
        ingredientes[element.uuid] = element
        if (storage) {
            downgrade()
        }
        return element
    }

    override fun saveAll(elements: List<Ingrediente>, storage: Boolean) {
        elements.forEach { save(it, false) } // Reducir el número de llamadas a downgrade()
        if (storage) {
            downgrade()
        }
    }

    override fun deleteById(id: UUID): Ingrediente? {
        val ingrediente = ingredientes.remove(id)
        downgrade()
        return ingrediente
    }

    override fun update(element: Ingrediente): Ingrediente? {
        return updateById(element.uuid, element)
    }

    override fun updateById(id: UUID, element: Ingrediente): Ingrediente? {
        val ingrediente = ingredientes[id] ?: return null
        element.updateAt = LocalDateTime.now()
        ingredientes[id] = element
        downgrade()
        return ingrediente
    }

    override fun upgrade(): List<Ingrediente> {
        deleteAll()
        val ingredientes = storageService.loadAll()
        saveAll(ingredientes, false)
        return ingredientes
    }

    override fun downgrade(): List<Ingrediente> {
        return storageService.saveAll(ingredientes.values.toList())
    }
}