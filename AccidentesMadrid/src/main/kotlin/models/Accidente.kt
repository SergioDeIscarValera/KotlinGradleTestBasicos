package models

import dto.AccidenteDto
import enums.TipoClima
import enums.TipoPersona
import enums.TipoSexo
import java.time.LocalDateTime

data class Accidente(
    val numExpediente: String,
    // Junta la fecha y la hora en un solo campo
    val fechaYHora: LocalDateTime,
    val localizacion: String,
    val numero: String,
    val codDistrito: Int,
    val distrito: String,
    val tipoAccidente: String,
    val clima: TipoClima,
    val tipoVehiculo: String,
    val tipoPersona: TipoPersona,
    val rangoEdad: String,
    val sexo: TipoSexo,
    val codLesividad : Int?,
    val lesividad: String?,
    val coordenadas: Coordenadas,
    val alcochol: Boolean,
    val drogas: Boolean,
){
    fun toDto() = AccidenteDto(
        numExpediente = numExpediente,
        fechaYHora = fechaYHora.toString(),
        localizacion = localizacion,
        numero = numero,
        codDistrito = codDistrito.toString(),
        distrito = distrito,
        tipoAccidente = tipoAccidente,
        clima = clima.toString(),
        tipoVehiculo = tipoVehiculo,
        tipoPersona = tipoPersona.toString(),
        rangoEdad = rangoEdad,
        sexo = sexo.toString(),
        codLesividad = codLesividad.toString(),
        lesividad = lesividad ?: "NULL",
        coordenadas = coordenadas.toDto,
        alcochol = alcochol.toString(),
        drogas = drogas.toString(),
    )
}