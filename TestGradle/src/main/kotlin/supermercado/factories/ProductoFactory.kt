package supermercado.factories

import supermercado.models.Producto

object ProductoFactory {
    fun crearProductoRdn(precio: IntRange = 1..25): Producto {
        return Producto(precio.random())
    }
    fun crearProductosRdn(cantidad: IntRange = 1..10, precio: IntRange = 1..25): List<Producto> {
        val productos = mutableListOf<Producto>()
        for (i in 1..cantidad.random()) {
            productos.add(crearProductoRdn(precio))
        }
        return productos.toList()
    }
}