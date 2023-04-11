package services.storage.ingrediente

import config.AppConfig
import dto.IngredienteDto
import models.Ingrediente
import java.io.File
import java.util.*

object IngredienteFileCsv: IngredienteStorageService{

    override fun saveAll(elements: List<Ingrediente>): List<Ingrediente> {
        return elements // Solo lectura
    }

    override fun loadAll(): List<Ingrediente> {
        val ingredienteCsv = IngredienteFileCsv::class.java.getResourceAsStream("/ingredientes.csv")
            ?: throw RuntimeException("Error al cargar el CSV o el fichero no existe")
        val ingredientes = mutableListOf<Ingrediente>()

        ingredienteCsv.bufferedReader().forEachLine {
            val row = it.split(",")
            ingredientes.add(
                IngredienteDto(
                    uuid = row[0],
                    nombre = row[1],
                    precio = row[2],
                    cantidad = row[3],
                    disponible = row[4]
                ).toClass()
            )
        }
        return ingredientes
    }
}