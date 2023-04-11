package config

import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.DriverManager
import java.util.*

private val logger = KotlinLogging.logger {}

private val LOCAL_PATH = "${System.getProperty("user.dir")}${File.separator}"

object AppConfig {
    lateinit var APP_AUTHOR: String
    lateinit var APP_DATA: String
    lateinit var APP_DB_URL: String

    init {
        loadProperties()
        initStorage()
        initDataBase()
    }

    private fun initStorage() {
        logger.debug { "Creando directorio data si no existe" }
        Files.createDirectories(Paths.get(APP_DATA))
    }

    private fun initDataBase(){
        logger.debug { "Creando base de datos si no existe" }
        val connexion = DriverManager.getConnection(APP_DB_URL)
        connexion.autoCommit = false

        val dbCreateIfNot = connexion.prepareStatement("""DROP TABLE IF EXISTS Ingredientes""")
        logger.info { "Borrado de la tabla Ingrediente: " + dbCreateIfNot.executeUpdate() }

        val tablaCreateIfNot = connexion.prepareStatement("""CREATE TABLE IF NOT EXISTS Ingredientes(nIdIngrediente INTEGER PRIMARY KEY AUTOINCREMENT, cUuid TEXT NOT NULL UNIQUE, cNombre TEXT NOT NULL, nPrecio REAL NOT NULL, nCantidad INTEGER NOT NULL, dCreateAt TEXT NOT NULL, dUpdateAt TEXT NOT NULL, bDisponible INTEGER NOT NULL)""")
        logger.info { "Creación de la tabla Ingrediente: " + tablaCreateIfNot.executeUpdate() }

        connexion.commit()
        connexion.autoCommit = true
        connexion.close()
        println()
    }

    private fun loadProperties() {
        logger.debug { "Cargando configuración de la aplicación" }
        val properties = Properties()
        properties.load(AppConfig::class.java.getResourceAsStream("/config.properties"))

        APP_AUTHOR = properties.getProperty("app.auth") ?: "nobody"
        APP_DB_URL = properties.getProperty("app.db.url") ?: "jdbc:sqlite:ejemplo.db"
        APP_DATA = properties.getProperty("app.storage.dir") ?: "data"
        APP_DATA = "$LOCAL_PATH$APP_DATA"

        logger.info { "Configuración: app.author = $APP_AUTHOR" }
        logger.info { "Configuración: app.storage.dir = $APP_DATA" }
    }
}