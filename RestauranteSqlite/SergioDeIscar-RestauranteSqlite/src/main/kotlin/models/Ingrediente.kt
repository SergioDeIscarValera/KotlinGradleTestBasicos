package models

import dto.IngredienteDto
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

data class Ingrediente(
    var id:Long = -1,
    val uuid: UUID,
    val nombre: String,
    val precio: Float,
    val cantidad: Int,
    val createAt: LocalDateTime = LocalDateTime.now(),
    var updateAt: LocalDateTime = LocalDateTime.now(),
    val disponible: Boolean
): Serializable {
    fun toDto(): IngredienteDto {
        return IngredienteDto(
            id.toString(),
            uuid.toString(),
            nombre,
            precio.toString(),
            cantidad.toString(),
            createAt.toString(),
            updateAt.toString(),
            disponible.toString()
        )
    }
}
