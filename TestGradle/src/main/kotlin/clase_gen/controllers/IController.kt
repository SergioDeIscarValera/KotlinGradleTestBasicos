package clase_gen.controllers

interface IController<T, ID> {
    fun add(t: T): T
    fun getAll(): Array<T?>
    fun find(id: ID): T?
    fun update(t: T, oldId: ID): T
    fun delete(id: ID): T
}