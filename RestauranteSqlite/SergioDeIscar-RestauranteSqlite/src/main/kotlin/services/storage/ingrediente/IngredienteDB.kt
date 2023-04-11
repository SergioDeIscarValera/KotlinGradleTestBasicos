package services.storage.ingrediente

import config.AppConfig
import dto.IngredienteDto
import models.Ingrediente
import java.sql.DriverManager
import java.sql.Statement

object IngredienteDB: IngredienteStorageService {
    override fun saveAll(elements: List<Ingrediente>): List<Ingrediente> {
        elements.forEach {
            val connexion = DriverManager.getConnection(AppConfig.APP_DB_URL)
            val sentencia = connexion.prepareStatement(
                """INSERT INTO Ingredientes(cUuid, cNombre, nPrecio, nCantidad, dCreateAt, dUpdateAt, bDisponible) VALUES (?, ?, ?, ?, ?, ?, ?)""",
                Statement.RETURN_GENERATED_KEYS
            )
            sentencia.setString(1, it.uuid.toString())
            sentencia.setString(2, it.nombre)
            sentencia.setFloat(3, it.precio)
            sentencia.setInt(4, it.cantidad)
            sentencia.setString(5, it.createAt.toString())
            sentencia.setString(6, it.updateAt.toString())
            sentencia.setBoolean(7, it.disponible)
            sentencia.executeUpdate()
            it.id = sentencia.generatedKeys.getLong(1)
            connexion.close()
        }
        return elements
    }

    override fun loadAll(): List<Ingrediente> {
        val connexion = DriverManager.getConnection(AppConfig.APP_DB_URL)
        val sentencia = connexion.prepareStatement("""SELECT * FROM Ingredientes""")
        val result = sentencia.executeQuery()
        val ingredientes = mutableListOf<Ingrediente>()

        while (result.next()){
            val id = result.getLong("nIdIngrediente").toString()
            val uuid = result.getString("cUuid")
            val nombre = result.getString("cNombre")
            val precio = result.getFloat("nPrecio").toString()
            val cantidad = result.getInt("nCantidad").toString()
            val createAt = result.getString("dCreateAt")
            val updateAt = result.getString("dUpdateAt")
            val disponible = if (result.getBoolean("bDisponible")) "1" else "0"
            ingredientes.add(
                IngredienteDto(
                    id, uuid, nombre, precio, cantidad, createAt, updateAt, disponible
                ).toClass()
            )
        }

        connexion.close()
        return ingredientes.toList()
    }
}