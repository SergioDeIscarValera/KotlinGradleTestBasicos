package PokemonMaker

import PokemonMaker.enums.Tipo
import PokemonMaker.models.Características
import PokemonMaker.models.Equipo
import PokemonMaker.models.Movimiento
import PokemonMaker.models.Pokemon
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class PokemonMakerTest {
    val equipoEmpty = Equipo("")

    val movimientoExample = Movimiento(
        "Golpe",
        Tipo.VENENO,
        10u,
        10u,
        "Golpea"
    )

    val movimientos = Array<Movimiento>(4){movimientoExample}

    val pokemonExample = Pokemon(
        "Eduardo",
        Tipo.VOLADOR,
        Tipo.VENENO,
        Características(10u,10u,10u,10u,10u, 10u),
        movimientos
    )

    val pokemons = Array<Pokemon>(6){pokemonExample}

    val equipoFull = Equipo("", pokemons)

    // region Pokemon
    @Test
    fun getPokemonTest(){
        assertAll(
            { assertEquals(pokemons[0], equipoFull.getPokemon(0)) },
            { assertNull(equipoEmpty.getPokemon(0)) }
        )
    }

    @Test
    fun addPokemonTest(){
        assertAll(
            { assertEquals(pokemonExample, equipoEmpty.addPokemon(pokemonExample)) },
            { assertNull(equipoFull.addPokemon(pokemonExample)) }
        )
    }

    @Test
    fun updatePokemonTest(){
        val newPokemon = Pokemon("Pedro", Tipo.TIERRA, Tipo.ROCA, Características(23u,23u,23u,23u,23u, 23u))
        assertAll(
            { assertEquals(newPokemon, equipoFull.updatePokemon(0, newPokemon)) },
            { assertNull(equipoEmpty.updatePokemon(4, newPokemon)) }
        )
    }

    @Test
    fun deletePokemonTest(){
        assertAll(
            { assertTrue(equipoFull.deletePokemon(0)) },
            { assertFalse(equipoEmpty.deletePokemon(3)) }
        )
    }
    // endregion

    // region Movimientos
    @Test
    fun getMovimientoPokemonTest(){
        assertAll(
            { assertEquals(movimientoExample, equipoFull.getMovimientoPokemon(0,0)) },
            { assertNull(equipoEmpty.getMovimientoPokemon(0,0)) }
        )
    }

    @Test
    fun addMovimientoPokemonTest(){
        equipoEmpty.addPokemon( Pokemon(
            "Eduardo",
            Tipo.VOLADOR,
            Tipo.VENENO,
            Características(10u,10u,10u,10u,10u, 10u)))
        assertAll(
            { assertEquals(movimientoExample, equipoEmpty.addMovimientoPokemon(0, movimientoExample)) },
            { assertNull(equipoEmpty.addMovimientoPokemon(4, movimientoExample)) },
            { assertNull(equipoFull.addMovimientoPokemon(0, movimientoExample)) }
        )
    }

    @Test
    fun updateMovimientoPokemonTest(){
        val newMovimiento = Movimiento("Patada", Tipo.LUCHA, 50u, 15u, "Da una patada")
        assertAll(
            { assertEquals(newMovimiento, equipoFull.updateMovimientoPokemon(0, 0, newMovimiento)) },
            { assertNull(equipoEmpty.updateMovimientoPokemon(0,0, newMovimiento)) }
        )
    }

    @Test
    fun deleteMovimientoPokemonTest(){
        assertAll(
            { assertTrue(equipoFull.deleteMovimientoPokemon(0,0)) },
            { assertFalse(equipoEmpty.deleteMovimientoPokemon(0,0)) },
            { assertFalse(equipoFull.deleteMovimientoPokemon(0,0)) },
        )
    }
    // endregion
}