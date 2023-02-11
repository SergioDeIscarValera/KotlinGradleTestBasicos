package supermercado.interfaces

interface IPila<T> {
    fun push(elemento: T): T
    fun pop(): T?
    fun peek(): T?
    fun indices(): IntRange
    fun isEmpty(): Boolean
}