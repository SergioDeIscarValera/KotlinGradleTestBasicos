package FootBall

import Objetos.FootBall.COUNT_JUGADORES
import Objetos.FootBall.enums.TipoDenomination
import Objetos.FootBall.models.Entrenador
import Objetos.FootBall.models.Jugador
import Objetos.FootBall.models.Selection
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class SelectionTest(){

    private val selectionEmpty = Selection("Test", Array(COUNT_JUGADORES){null}, Entrenador("Paco", 14))
    private val selectionFull = Selection("Test", Array(COUNT_JUGADORES){Jugador("Full",0,TipoDenomination.DEFENSA)}, Entrenador("Paco", 14))

    // region Get
    @Test
    fun seleccionGetById(){
        val idJugadorFull = selectionFull.jugadores[0]?.id ?: -1
        assertAll(
            { assertEquals(Jugador("Full", 0, TipoDenomination.DEFENSA), selectionFull.getJugadorById(idJugadorFull)) },
            { assertEquals(null, selectionFull.getJugadorById(-1)) }
        )
    }

    @Test
    fun seleccionGetJugadoresByDorsal(){
        val test1 = Array<Jugador?>(COUNT_JUGADORES){ Jugador("Full", 0, TipoDenomination.DEFENSA) }
        val test2 = Array<Jugador?>(COUNT_JUGADORES){null}
        assertAll(
            { assertContentEquals(test1, selectionFull.getJugadoresByDorsal(0)) },
            { assertContentEquals(test2, selectionFull.getJugadoresByDorsal(-1)) }
        )
    }

    @Test
    fun seleccionGetJugadoresByDenomination(){
        val test1 = Array<Jugador?>(COUNT_JUGADORES){ Jugador("Full", 0, TipoDenomination.DEFENSA) }
        val test2 = Array<Jugador?>(COUNT_JUGADORES){null}
        assertAll(
            { assertContentEquals(test1, selectionFull.getJugadoresByDenomination(TipoDenomination.DEFENSA)) },
            { assertContentEquals(test2, selectionFull.getJugadoresByDenomination(TipoDenomination.CENTROCAMPISTA)) }
        )
    }

    // endregion

    // region Set
    @Test
    fun seleccionAddJugador(){
        val jugador1 = Jugador("Pedro", 5, TipoDenomination.DEFENSA)
        assertAll(
            { assertEquals(jugador1, selectionEmpty.addJugador(jugador1)) },
            { assertEquals(null, selectionFull.addJugador(jugador1)) },
        )
    }

    @Test
    fun seleccionUpdateJugadorById(){
        val jugador1 = Jugador("New", 7, TipoDenomination.CENTROCAMPISTA)
        val idJugadorFull = selectionFull.jugadores[0]?.id ?: -1
        val idJugadorFull2 = selectionFull.jugadores[1]?.id ?: -1
        assertAll(
            { assertEquals(jugador1, selectionFull.updateJugadorById(idJugadorFull, jugador1)) },
            { assertEquals(jugador1, selectionFull.updateJugadorById(idJugadorFull2, jugador1.name, jugador1.dorsal, jugador1.denomination)) },
            { assertEquals(null, selectionFull.updateJugadorById(idJugadorFull, jugador1)) },
        )
    }

    @Test
    fun seleccionDeleteJugadorById(){
        val idJugadorFull = selectionFull.jugadores[0]?.id ?: -1
        assertAll(
            { assertTrue(selectionFull.deleteJugadorById(idJugadorFull)) },
            { assertFalse(selectionFull.deleteJugadorById(-1)) },
            { assertFalse(selectionEmpty.deleteJugadorById(10)) }
        )
    }

    // endregion
}