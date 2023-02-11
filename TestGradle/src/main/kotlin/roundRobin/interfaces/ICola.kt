package roundRobin.interfaces

interface ICola<T> {
    fun enqueue(element: T): T
    fun dequeue(): T?
    fun first(): T?
    fun isEmpty(): Boolean
}