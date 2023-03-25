package services.storage.accidente

import config.AppConfig
import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import formateadores.toClima
import formateadores.toFechaYHora
import formateadores.toPersona
import formateadores.toSexo
import models.Accidente
import models.Coordenadas
import mu.KotlinLogging
import java.io.File
import java.lang.RuntimeException
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

object AccidenteFileCsv: AccidenteStorageService {

    override fun saveAll(elements: List<Accidente>): List<Accidente> {
        logger.debug { "AccidenteFileCsv ->\tsaveAll: ${elements.joinToString("\t")}" }
        // Por ahora no se va a guardar nada en el fichero
        return elements
    }

    override fun loadAll(): List<Accidente> {
        logger.debug { "AccidenteFileCsv ->\tloadAll" }

        val accidenteCSV = AccidenteFileCsv::class.java.getResourceAsStream("/accidentes.csv")
            ?: throw RuntimeException("Error al cargar el CSV o el fichero no existe")

        val accidentes = mutableListOf<Accidente>()
        accidenteCSV.bufferedReader().forEachLine {
            // saltar la primera línea
            if (it.startsWith("num")) return@forEachLine

            val row = it.split(";")
            accidentes.add(
                Accidente(
                    numExpediente = row[0],
                    fechaYHora = row[1].toFechaYHora(row[2]),
                    localizacion = row[3],
                    numero = row[4],
                    codDistrito = row[5].toIntOrNull() ?: -1,
                    distrito = row[6],
                    tipoAccidente = row[7],
                    clima = row[8].toClima(),
                    tipoVehiculo = row[9],
                    tipoPersona = row[10].toPersona(),
                    rangoEdad = row[11],
                    sexo = row[12].toSexo(),
                    codLesividad = row[13].toIntOrNull() ?: -1,
                    lesividad = row[14],
                    coordenadas = Coordenadas(
                        row[15].replace(",",".").toDoubleOrNull(),
                        row[16].replace(",",".").toDoubleOrNull()
                    ),
                    alcochol = row[17] == "S",
                    drogas = row[18] == "1",
                )
            )
        }
        return accidentes
    }
}