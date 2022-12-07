package PokemonMaker.models

import PokemonMaker.enums.Tipo
import java.lang.StringBuilder

data class Pokemon(val nombre: String, val tipo: Tipo, val subTipo: Tipo, val características: Características){
    private val movimientos = Array<Movimiento?>(4){ null }

    fun getMovimientos(): Array<Movimiento>{
        val listMovimiento = mutableListOf<Movimiento>()
        for (i in this.movimientos.indices){
            if (this.movimientos[i] != null){
                movimientos[i]?.let { listMovimiento.add(it) }
            }
        }
        return listMovimiento.toTypedArray()
    }
    fun getMovimiento(index: Int): Movimiento?{
        return movimientos[index]
    }
    fun addMovimiento(movimiento: Movimiento): Movimiento?{
        for (i in this.movimientos.indices){
            if (this.movimientos[i] == null){
                this.movimientos[i] = movimiento
                return movimiento
            }
        }
        return null
    }
    fun updateMovimiento(index: Int, movimiento: Movimiento): Movimiento?{
        if (getMovimiento(index) == null) return null
        movimientos[index] = movimiento
        return movimiento
    }
    fun deleteMovimiento(index: Int): Boolean{
        if (getMovimiento(index) == null) return false
        movimientos[index] = null
        return true
    }

    override fun toString(): String {
        val movimientosNotNull = getMovimientos()
        val movimientoString: StringBuilder = StringBuilder()
        for (i in movimientosNotNull){
            movimientoString.append("${i.toJsonInArray()},")
        }
        return """"Pokemon":{ "nombre":"$nombre", "tipo":"$tipo", "subTipo":"$subTipo", $características, "Movimientos":[ $movimientoString ] }"""
    }
}
