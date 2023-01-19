package carrito_test

import carrito.addProducto
import carrito.cambiarProducto
import carrito.eliminarProducto
import carrito.numOfProductos
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private class CarritoTest {

    @Test
    fun numOfProductos_Test(){
        val carritoEmpty: Array<Array<Any>> = Array(10){ arrayOf("", 0.0, 0, 0.0, 0 ,0.0) }
        val carritoFull: Array<Array<Any>> = Array(10){ arrayOf("Test", 1.0, 1, 1.0, 1, 1.1) }

        assertAll(
            { assertEquals(0, numOfProductos(carritoEmpty)) },
            { assertEquals(10, numOfProductos(carritoFull)) }
        )
    }

    @Test
    fun  addProducto_Test(){
        val carritoEmpty: Array<Array<Any>> = Array(10){ arrayOf("", 0.0, 0, 0.0, 0, 0.0) }

        val carritoCase: Array<Array<Any>> = Array(10){ arrayOf("", 0.0, 0, 0.0, 0, 0.0) }
        carritoCase[0] = arrayOf("Lápiz", 0.25, 5, 1.25, 0, 1.25)
        carritoCase[1] = arrayOf("Manzana", 1.0, 3, 3.0, 1, 3.3)
        carritoCase[2] = arrayOf("Ratón", 2.5, 1, 2.5, 2, 3.03)

        assertAll(
            { assertTrue(addProducto(carritoEmpty, "Lápiz", 5)) },   // Primero lo añado
            { assertContentEquals(carritoCase[0],carritoEmpty[0]) },                // Y luego miro si se a añadido bien

            { assertTrue(addProducto(carritoEmpty, "Manzana", 3)) }, // Primero lo añado
            { assertContentEquals(carritoCase[1],carritoEmpty[1]) },                // Y luego miro si se a añadido bien

            { assertTrue(addProducto(carritoEmpty, "Ratón", 1)) },   // Primero lo añado
            { assertContentEquals(carritoCase[2],carritoEmpty[2]) },                // Y luego miro si se a añadido bien

            { assertFalse(addProducto(carritoEmpty, "sdasd", 5)) },
            { assertFalse(addProducto(carritoEmpty, "Lápiz", 0)) }
        )
    }

    @Test
    fun eliminarProducto_Test(){
        val carritoFull: Array<Array<Any>> = Array(10){ arrayOf("Lápiz", 0.25, 5, 1.25, 0, 1.25) }
        val carritoEmpty: Array<Array<Any>> = Array(10){ arrayOf("", 0.0, 0, 0.0, 0 ,0.0) }

        assertAll(
            { assertTrue(eliminarProducto(carritoFull, 0)) },
            { assertContentEquals(carritoFull[0],carritoEmpty[0])},

            { assertTrue(eliminarProducto(carritoFull, 9)) },
            { assertContentEquals(carritoFull[9],carritoEmpty[9])},

            { assertFalse(eliminarProducto(carritoEmpty, 0)) },
            { assertFalse(eliminarProducto(carritoEmpty, 10)) }
        )
    }

    @Test
    fun cambiarProducto_Test(){
        val carritoFull: Array<Array<Any>> = Array(10){ arrayOf("Lápiz", 0.25, 5, 1.25, 0, 1.25) }

        val carritoCase: Array<Array<Any>> = Array(10){ arrayOf("", 0, 0, 0, 0, 0) }
        carritoCase[0] = arrayOf("Manzana", 1.0, 3, 3.0, 1, 3.3)
        carritoCase[9] = arrayOf("Ratón", 2.5, 1, 2.5, 2, 3.03)

        assertAll(
            { assertTrue(cambiarProducto(carritoFull, 0, "Manzana", 3)) },
            { assertContentEquals(carritoCase[0], carritoFull[0]) },

            { assertTrue(cambiarProducto(carritoFull, 9, "Ratón", 1)) },
            { assertContentEquals(carritoCase[9], carritoFull[9]) },

            { assertFalse(cambiarProducto(carritoFull, 0, "asdads", 0)) },
            { assertFalse(cambiarProducto(carritoFull, 10, "Manzana", 4)) }
        )
    }
}