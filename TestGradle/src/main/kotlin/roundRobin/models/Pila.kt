package roundRobin.models

import supermercado.interfaces.IPila

class Pila<T>: IPila<T> {
    private val elements: MutableList<T> = mutableListOf()
    override fun push(elemento: T): T {
        elements.add(elemento)
        return elemento
    }

    override fun pop(): T? {
        if (isEmpty()) return null
        return elements.removeLast()
    }

    override fun peek(): T? {
        if (isEmpty()) return null
        return elements.last()
    }

    override fun indices(): IntRange {
        return elements.indices
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun unpacking() {
        if (isEmpty()) return
        for (i in elements.indices) {
            println(pop())
        }
    }

    fun toList(): List<T> {
        return elements.toList()
    }

    override fun toString(): String {
        return elements.joinToString("\n")
    }
}