package repository

interface IExternalStore<T, ID> {
    fun upgrade(): List<T>
    fun downgrade(): List<T>
}