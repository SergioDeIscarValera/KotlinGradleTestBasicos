package Objetos.FootBall.models

import Objetos.FootBall.COUNT_JUGADORES
import Objetos.FootBall.enums.TipoDenomination

/**
 * Conjunto de jugadores con un entrenador y un país
 *
 * @param pais País de la selección
 * @param jugadores Conjunto de los jugadores que están en esta selección
 * @param entrenador Entrenador de la selección
 *
 * @see Jugador
 * @see Entrenador
 */
data class Selection (var pais: String, val jugadores: Array<Jugador?>, var entrenador: Entrenador){
    // region Get

    /**
     * Función para obtener un jugador buscando por su ID
     *
     * @param idFind El parámetro idFind define el id a buscar
     * @return Si el jugador es encontrado devuelve la clase Jugador
     * @return Si no es encontrado devuelve null
     */
    fun getJugadorById( idFind: Int ): Jugador?{
        for (i in jugadores){
            if (i != null && i.id == idFind){
                return i
            }
        }
        return null
    }

    /**
     * Función para obtener un array de jugadores con la misma dorsal
     *
     * @param dorsalFind El parámetro dorsalFind define la dorsal por la que filtrar la búsqueda
     * @return Devuelve un array de la clase Jugador con todos los resultados de la búsqueda filtrada
     */
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

    /**
     * Función para obtener un array de jugadores con la misma denominación
     *
     * @param denominationFind El parámetro denominationFind define la denominación por la que filtrar la búsqueda
     * @return Devuelve un array de la clase Jugadores con todos los resultados de la búsqueda filtrada
     */
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

    // region Set

    /**
     * Función para añadir un jugador a la selección
     *
     * @param jugador Es el jugador a añadir
     * @return Si el jugador es añadido de forma correcta devuelve la propia clase jugador enviado como parámetro
     * @return Si no se a añadido de forma correcta devuelve null
     */
    fun addJugador(jugador: Jugador): Jugador?{
        for (i in jugadores.indices){
            if (jugadores[i] == null){
                jugadores[i] = jugador
                return jugador
            }
        }
        return null
    }

    /**
     * Función para actualizar un jugador buscado por ID
     *
     * @param id ID a buscar entre los jugadores
     * @param newName Nuevo nombre a actualizar
     * @param newDorsal Nueva dorsal a actualizar
     * @param newDenomination Nueva denominación a actualizar
     * @see updateJugadorById
     * @return Devuelve lo que de la función updateJugadorById(id: Int, jugador: Jugador)
     */
    fun updateJugadorById(id: Int, newName: String, newDorsal: Int, newDenomination: TipoDenomination): Jugador?{
        return updateJugadorById(id, Jugador(newName, newDorsal, newDenomination))
    }

    /**
     * Función para actualizar un jugador por ID
     *
     * @param id ID a buscar entre los jugadores
     * @param jugador Clase Jugador que va a ser los nuevos valores
     * @return Si el jugador es encontrado y actualizado devuelve la clase Jugador pasada como parámetro
     * @return Si el jugador no es encontrado o no se actualiza correctamente devuelve null
     */
    fun updateJugadorById(id: Int, jugador: Jugador): Jugador?{
        for (i in jugadores.indices){
            if (jugadores[i] != null && jugadores[i]?.id == id){
                jugadores[i] = jugador
                return jugador
            }
        }
        return null
    }

    /**
     * Función para eliminar un jugador por ID
     *
     * @param id ID a buscar entre los jugadores
     * @return Devuelve verdadero si el jugador es encontrado he eliminado
     * @return Devuelve falso si el jugador no es encontrado
     */
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