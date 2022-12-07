package PokemonMaker.models

import PokemonMaker.enums.Base
import PokemonMaker.enums.Tipo

data class Movimiento(val nombre: String, val tipo: Tipo, val poder: UInt, val pp: UInt, val efecto: String){
    private val base = when(tipo){
        Tipo.ACERO, Tipo.BICHO, Tipo.FANTASMA, Tipo.LUCHA, Tipo.NORMAL, Tipo.ROCA, Tipo.TIERRA, Tipo.VENENO, Tipo.VOLADOR -> Base.FÍSICO
        else -> Base.ESPECIAL
    }

    override fun toString(): String {
        return """ "Movimiento":{ "nombre":"$nombre", "tipo":"$tipo", "base":"$base", "poder":$poder, "pp":$pp, "efecto":"$efecto" } """
    }

    fun toJsonInArray(): String {
        return """{ "nombre":"$nombre", "tipo":"$tipo", "base":"$base", "poder":$poder, "pp":$pp, "efecto":"$efecto" }"""
    }
}
