package dto

import models.Ingrediente
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class IngredienteDto(
    val id:String = "-1",
    val uuid: String,
    val nombre: String,
    val precio: String,
    val cantidad: String,
    val createAt: String = LocalDateTime.now().toString(),
    val updateAt: String = LocalDateTime.now().toString(),
    val disponible: String
): Serializable {
    fun toClass(): Ingrediente {
        return Ingrediente(
            id.toLong(),
            UUID.fromString(uuid),
            nombre,
            precio.toFloat(),
            cantidad.toInt(),
            LocalDateTime.parse(createAt),
            LocalDateTime.parse(updateAt),
            disponible == "1"
        )
    }
}