package supermercado.factories

import supermercado.models.Cliente

object ClientesFactory {
    fun crearClienteRdn(): Cliente {
        return Cliente(ProductoFactory.crearProductosRdn().toMutableList())
    }

    fun crearClientesRdn(cantidad: IntRange = 1..10): List<Cliente> {
        val clientes = mutableListOf<Cliente>()
        for (i in 1..cantidad.random()) {
            clientes.add(crearClienteRdn())
        }
        return clientes.toList()
    }
}