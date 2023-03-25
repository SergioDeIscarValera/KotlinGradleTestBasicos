package dto

import models.Coordenadas

data class CoordenadasDto(
    val cordenadaX: String,
    val cordenadaY: String,
) {
    fun toClass() = Coordenadas(
        cordenadaX = cordenadaX.toDouble(),
        cordenadaY = cordenadaY.toDouble(),
    )
}