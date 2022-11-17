package DAM_Zum_Test

import DAM_Zum.dam_zum
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class dam_zum_test{
    private val damZum = dam_zum()

    @Test
    fun inputRegexTest(){
        assertAll(
            { assertTrue(damZum.inputCantidad("450.01")) },
            { assertTrue(damZum.inputCantidad("450,01")) },
            { assertTrue(damZum.inputCantidad("499")) },

            { assertFalse(damZum.inputCantidad("500.01")) },
            { assertFalse(damZum.inputCantidad("0.49")) },
            { assertFalse(damZum.inputCantidad("0,49")) },
            { assertFalse(damZum.inputCantidad("1.002")) }
        )
    }

    @Test
    fun enviarTest(){
        assertAll(
            { assertEquals(600f,damZum.enviar(300f,300f,300f)) },
            { assertEquals(330.5f,damZum.enviar(30.5f,300f,300f)) },

            { assertEquals(-1f,damZum.enviar(500f,500f,300f)) },
            { assertEquals(-1f,damZum.enviar(0.49f,300f,300f)) },
            { assertEquals(-1f,damZum.enviar(40.001f,300f,300f)) },

            { assertEquals(-1f,damZum.enviar(400f,300f,300f)) }
        )
    }

    @Test
    fun recibirTest(){
        assertAll(
            { assertEquals(600f,damZum.recibir(300f,300f)) },
            { assertEquals(330.5f,damZum.recibir(30.5f,300f)) },

            { assertEquals(-1f,damZum.recibir(500f,300f)) },
            { assertEquals(-1f,damZum.recibir(0.49f,300f)) },
            { assertEquals(-1f,damZum.recibir(40.001f,300f)) }
        )
    }
}