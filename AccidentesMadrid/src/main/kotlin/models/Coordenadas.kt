package models

import dto.CoordenadasDto

data class Coordenadas(
    val cordenadaX: Double?,
    val cordenadaY: Double?,
) {
    val toDto = CoordenadasDto(
        cordenadaX = cordenadaX.toString(),
        cordenadaY = cordenadaY.toString(),
    )
}
