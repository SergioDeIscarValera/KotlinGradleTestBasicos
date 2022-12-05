package Objetos.FootBall.models

import Objetos.FootBall.enums.TipoDenomination

/**
 * Jugador con un nombre, dorsal y una denominación
 *
 * Esta clase no tiene métodos "extras"
 *
 * @param name Nombre del jugador
 * @param dorsal Número de dorsal del jugador
 * @param denomination Tipo de denominación del jugador
 *
 * @see TipoDenomination
 */
data class Jugador (var name: String, var dorsal: Int, var denomination: TipoDenomination){
    val id = Jugador.nextId()
    // static:
    companion object{
        var count = 0
        private fun nextId(): Int {
            return count++
        }
    }

    override fun toString(): String {
        return "Jugador: ID: $id; name: $name; dorsal: $dorsal; denominación: $denomination"
    }
}