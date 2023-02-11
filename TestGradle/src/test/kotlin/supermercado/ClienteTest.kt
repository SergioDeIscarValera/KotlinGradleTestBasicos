package supermercado

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import supermercado.models.Cliente
import supermercado.models.Producto
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClienteTest {
    private lateinit var clientes: Array<Cliente>

    @BeforeEach
    fun setUp() {
        clientes = arrayOf(
            Cliente(mutableListOf(
                Producto(15),
                Producto(7),
                Producto(9)
            )),
            Cliente(
                mutableListOf(
                    Producto(74)
                )
            ),
            Cliente()
        )
    }

    @Test
    fun pushTest(){
        val producto = Producto(10)
        assertAll(
            { assertEquals(producto, clientes[0].push(producto)) },
            { assertEquals(producto, clientes[1].push(producto)) },
            { assertEquals(producto, clientes[2].push(producto)) }
        )
    }

    @Test
    fun popTest(){
        assertAll(
            { assertEquals(9, clientes[0].pop()?.precio) },
            { assertEquals(74, clientes[1].pop()?.precio) },
            { assertEquals(null, clientes[2].pop()) }
        )
    }

    @Test
    fun peekTest(){
        assertAll(
            { assertEquals(9, clientes[0].peek()?.precio) },
            { assertEquals(74, clientes[1].peek()?.precio) },
            { assertEquals(null, clientes[2].peek()) }
        )
    }
}