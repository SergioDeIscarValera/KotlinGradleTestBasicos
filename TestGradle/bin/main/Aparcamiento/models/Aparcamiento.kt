package Aparcamiento.models

import Aparcamiento.enums.TipoVehiculo

class Aparcamiento(){
    private val cuadricula = Array(4){ Array<Vehiculo?>(4){null} }
    private val precio = 3.75f

    fun aparcarVehiculo(vehiculo: Vehiculo): Vehiculo?{
        for (i in cuadricula.indices){
            for (j in cuadricula[i].indices){
                if (cuadricula[i][j] == null) {
                    cuadricula[i][j] = vehiculo
                    return vehiculo
                }
            }
        }
        return null
    }

    fun sacarVehiculo(matricula: String): Boolean{
        for (i in cuadricula.indices){
            for (j in cuadricula[i].indices){
                if ((cuadricula[i][j]?.matricula ?: "") == matricula) {
                    cuadricula[i][j] = null
                    return true
                }
            }
        }
        return false
    }

    fun getVehiculoCount(): Int{
        var count = 0
        for (i in cuadricula){
            for (j in i){
                if (j != null) count++
            }
        }
        return count
    }

    fun getCuadricula(): Array<Array<Vehiculo?>>{ return cuadricula }

    fun getVehiculo(matricula: String): Vehiculo?{
        for (i in cuadricula.indices){
            for (j in cuadricula[i].indices){
                if ((cuadricula[i][j]?.matricula ?: "") == matricula) {
                    return cuadricula[i][j]
                }
            }
        }
        return null
    }

    fun getVehiculoConductor(conductor: Conductor): Int{
        var count = 0
        for (i in cuadricula){
            for (j in i){
                if (j?.conductor == conductor) count++
            }
        }
        return count
    }

    fun getVehiculoSort(): Array<Vehiculo>{
        val sortArray = arrayNullableToArray(matrizToArray(cuadricula))
        for(i in 0 until sortArray.size - 1){
            if(sortArray[i].anio > sortArray[i + 1].anio){
                val temp = sortArray[i]
                sortArray[i] = sortArray[i+1]
                sortArray[i + 1] = temp
            }
        }
        return sortArray
    }

    private fun matrizToArray(matriz: Array<Array<Vehiculo?>>): Array<Vehiculo?> {
        val array = Array<Vehiculo?>(cuadricula.size * cuadricula[0].size){ null }
        var puntero = 0
        for (i in matriz){
            for (j in i){
                array[puntero] = j
                puntero++
            }
        }
        return array
    }

    private fun arrayNullableToArray(arrayNullable: Array<Vehiculo?>): Array<Vehiculo>{
        var count = 0
        for (i in arrayNullable){
            if (i != null) count++
        }

        var puntero = 0
        val array = Array<Vehiculo>(count){ Vehiculo(Conductor("",""), "", 0, TipoVehiculo.COCHE) }
        for (i in arrayNullable){
            if (i != null) {
                array[puntero] = i
                puntero++
            }
        }
        return array
    }

    fun getBalance(): Float{
        return getVehiculoCount() * precio
    }
}
