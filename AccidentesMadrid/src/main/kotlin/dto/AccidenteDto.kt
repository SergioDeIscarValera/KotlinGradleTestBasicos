package dto

import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import models.Accidente
import java.time.LocalDateTime

data class AccidenteDto(
    val numExpediente: String,
    val fechaYHora: String,
    val localizacion: String,
    val numero: String,
    val codDistrito: String,
    val distrito: String,
    val tipoAccidente: String,
    val clima: String,
    val tipoVehiculo: String,
    val tipoPersona: String,
    val rangoEdad: String,
    val sexo: String,
    val codLesividad : String,
    val lesividad: String,
    val coordenadas: CoordenadasDto,
    val alcochol: String,
    val drogas: String,
) {
    fun toClass() = Accidente(
        numExpediente = numExpediente,
        fechaYHora = LocalDateTime.parse(fechaYHora),
        localizacion = localizacion,
        numero = numero,
        codDistrito = codDistrito.toInt(),
        distrito = distrito,
        tipoAccidente = tipoAccidente,
        clima = TipoClima.valueOf(clima),
        tipoVehiculo = tipoVehiculo,
        tipoPersona = TipoPersona.valueOf(tipoPersona),
        rangoEdad = rangoEdad,
        sexo = TipoSexo.valueOf(sexo),
        codLesividad = codLesividad.toInt(),
        lesividad = lesividad,
        coordenadas = coordenadas.toClass(),
        alcochol = alcochol.toBoolean(),
        drogas = drogas.toBoolean(),
    )
}