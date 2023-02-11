package supermercado.models

import supermercado.interfaces.IPila

class Cliente(private val productos: MutableList<Producto> = mutableListOf()): IPila<Producto> {
    override fun push(elemento: Producto): Producto {
        productos.add(elemento)
        return elemento
    }

    override fun pop(): Producto? {
        if (productos.isEmpty()) return null
        return productos.removeLast()
    }

    override fun peek(): Producto? {
        if (productos.isEmpty()) return null
        return productos.last()
    }

    override fun indices(): IntRange {
        return productos.indices
    }

    override fun isEmpty(): Boolean {
        return productos.isEmpty()
    }

    override fun unpacking() {
        if (productos.isEmpty()) return
        for (i in productos.indices) {
            println(pop())
        }
    }
}