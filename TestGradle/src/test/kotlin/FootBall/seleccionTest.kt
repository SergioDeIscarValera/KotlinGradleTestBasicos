package FootBall

import Objetos.FootBall.COUNT_JUGADORES
import Objetos.FootBall.enums.TipoDenomination
import Objetos.FootBall.models.Entrenador
import Objetos.FootBall.models.Jugador
import Objetos.FootBall.models.Seleccion
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class seleccionTest(){

    private val seleccionEmpty = Seleccion("Test", Array(COUNT_JUGADORES){null}, Entrenador("Paco", 14))
    private val seleccionFull = Seleccion("Test", Array(COUNT_JUGADORES){Jugador("Full",0,TipoDenomination.DEFENSA)}, Entrenador("Paco", 14))

    // region Get
    @Test
    fun seleccionGetById(){
        val idJugadorFull = seleccionFull.jugadores[0]?.id ?: -1
        assertAll(
            { assertEquals(Jugador("Full", 0, TipoDenomination.DEFENSA), seleccionFull.getJugadorById(idJugadorFull)) },
            { assertEquals(null, seleccionFull.getJugadorById(-1)) }
        )
    }

    @Test
    fun seleccionGetJugadoresByDorsal(){
        val test1 = Array<Jugador?>(COUNT_JUGADORES){ Jugador("Full", 0, TipoDenomination.DEFENSA) }
        val test2 = Array<Jugador?>(COUNT_JUGADORES){null}
        assertAll(
            { assertContentEquals(test1, seleccionFull.getJugadoresByDorsal(0)) },
            { assertContentEquals(test2, seleccionFull.getJugadoresByDorsal(-1)) }
        )
    }

    @Test
    fun seleccionGetJugadoresByDenomination(){
        val test1 = Array<Jugador?>(COUNT_JUGADORES){ Jugador("Full", 0, TipoDenomination.DEFENSA) }
        val test2 = Array<Jugador?>(COUNT_JUGADORES){null}
        assertAll(
            { assertContentEquals(test1, seleccionFull.getJugadoresByDenomination(TipoDenomination.DEFENSA)) },
            { assertContentEquals(test2, seleccionFull.getJugadoresByDenomination(TipoDenomination.CENTROCAMPISTA)) }
        )
    }

    // endregion

    // region Set
    @Test
    fun seleccionAddJugador(){
        val jugador1 = Jugador("Pedro", 5, TipoDenomination.DEFENSA)
        assertAll(
            { assertEquals(jugador1, seleccionEmpty.addJugador(jugador1)) },
            { assertEquals(null, seleccionFull.addJugador(jugador1)) },
        )
    }

    @Test
    fun seleccionUpdateJugadorById(){
        val jugador1 = Jugador("New", 7, TipoDenomination.CENTROCAMPISTA)
        val idJugadorFull = seleccionFull.jugadores[0]?.id ?: -1
        val idJugadorFull2 = seleccionFull.jugadores[1]?.id ?: -1
        assertAll(
            { assertEquals(jugador1, seleccionFull.updateJugadorById(idJugadorFull, jugador1)) },
            { assertEquals(jugador1, seleccionFull.updateJugadorById(idJugadorFull2, jugador1.name, jugador1.dorsal, jugador1.denomination)) },
            { assertEquals(null, seleccionFull.updateJugadorById(idJugadorFull, jugador1)) },
        )
    }

    @Test
    fun seleccionDeleteJugadorById(){
        val idJugadorFull = seleccionFull.jugadores[0]?.id ?: -1
        assertAll(
            { assertTrue(seleccionFull.deleteJugadorById(idJugadorFull)) },
            { assertFalse(seleccionFull.deleteJugadorById(-1)) },
            { assertFalse(seleccionEmpty.deleteJugadorById(10)) }
        )
    }

    // endregion
}