package controllers

import models.Ingrediente
import repositories.ingrediente.IngredienteExtension
import repositories.ingrediente.IngredienteRepository
import java.util.*

class IngredienteController(
    private val repo: IngredienteRepository
): IngredienteExtension {
    override fun findByLong(id: Long): Ingrediente? {
        TODO("Not yet implemented")
    }

    override fun findByNombre(nombre: String): List<Ingrediente> {
        return repo.findByNombre(nombre)
    }

    override fun findByDisponible(disponible: Boolean): List<Ingrediente> {
        return repo.findByDisponible(disponible)
    }

    override fun deleteByLong(id: Long): Ingrediente? {
        return repo.deleteByLong(id)
    }

    override fun deleteAll(): Boolean {
        return repo.deleteAll()
    }

    override fun getAll(): List<Ingrediente> {
        return repo.getAll()
    }

    override fun getById(id: UUID): Ingrediente? {
        return repo.getById(id)
    }

    override fun save(element: Ingrediente, storage: Boolean): Ingrediente {
        return repo.save(element, storage)
    }

    override fun saveAll(elements: List<Ingrediente>, storage: Boolean) {
        return repo.saveAll(elements, storage)
    }

    override fun deleteById(id: UUID): Ingrediente? {
        return repo.deleteById(id)
    }

    override fun update(element: Ingrediente): Ingrediente? {
        return repo.update(element)
    }

    override fun updateById(id: UUID, element: Ingrediente): Ingrediente? {
        return repo.updateById(id, element)
    }
}