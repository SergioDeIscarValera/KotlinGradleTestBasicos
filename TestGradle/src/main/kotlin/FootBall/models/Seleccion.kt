package Objetos.FootBall.models

import Objetos.FootBall.COUNT_JUGADORES
import Objetos.FootBall.enums.TipoDenomination

data class Seleccion (var pais: String, val jugadores: Array<Jugador?>, var entrenador: Entrenador){
    // region Get
    fun getJugadorById( idFind: Int ): Jugador?{
        for (i in jugadores){
            if (i != null && i.id == idFind){
                return i
            }
        }
        return null
    }

    fun getJugadoresByDorsal(dorsalFind: Int): Array<Jugador?>{
        val newArray = Array<Jugador?>(COUNT_JUGADORES){ null }
        var count = 0
        for (i in jugadores){
            if (i != null && i.dorsal == dorsalFind){
                newArray[count] = i
                count++
            }
        }
        return newArray
    }

    fun getJugadoresByDenomination(denominationFind: TipoDenomination): Array<Jugador?>{
        val newArray = Array<Jugador?>(COUNT_JUGADORES){ null }
        var count = 0
        for (i in jugadores){
            if (i != null && i.denomination == denominationFind){
                newArray[count] = i
                count++
            }
        }
        return newArray
    }
    // endregion

    // region set
    fun addJugador(jugador: Jugador): Jugador?{
        for (i in jugadores.indices){
            if (jugadores[i] == null){
                jugadores[i] = jugador
                return jugador
            }
        }
        return null
    }

    fun updateJugadorById(id: Int, newName: String, newDorsal: Int, newDenomination: TipoDenomination): Jugador?{
        return updateJugadorById(id, Jugador(newName, newDorsal, newDenomination))
    }

    fun updateJugadorById(id: Int, jugador: Jugador): Jugador?{
        for (i in jugadores.indices){
            if (jugadores[i] != null && jugadores[i]?.id == id){
                jugadores[i] = jugador
                return jugador
            }
        }
        return null
    }

    fun deleteJugadorById(id: Int): Boolean{
        for (i in jugadores.indices){
            if (jugadores[i] != null && jugadores[i]?.id == id){
                jugadores[i] = null
                return true
            }
        }
        return false
    }
    // endregion
}