package clase_gen.repositories

interface ICrudRepository<T, ID> {
    fun add(t: T): T?
    fun getAll(): Array<T?>
    fun find(id: ID): T?
    fun update(t: T, oldId: ID): T?
    fun delete(id: ID): T?
    fun saveAll(array: Array<T>)
}