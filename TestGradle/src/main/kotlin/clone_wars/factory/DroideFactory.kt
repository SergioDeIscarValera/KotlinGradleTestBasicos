package clone_wars.factory

import clone_wars.models.Droide
import clone_wars.models.SW348
import clone_wars.models.SW4421
import clone_wars.models.SW447

object DroideFactory {
    fun randomDroides(cuadricula: Array<Array<Droide?>>, count: Int, porcentajes: IntArray = intArrayOf(30, 50, 20)){
        require(porcentajes.size == 3){"Error -> Porcentajes requiere ser de tamaño 3."}
        require(porcentajes.sum() == 100){"Error -> La suma de los porcentajes no es 100."}
        require(porcentajes.all { it != 0 }){"Error -> Porcentajes no puede tener un campo a 0."}
        for (i in 0 until count){
            var pos: Pair<Int, Int>
            do {
                pos = Pair(cuadricula.indices.random(),cuadricula[0].indices.random())
            }while (cuadricula[pos.first][pos.second] != null)
            val droide = getRandomDroide(porcentajes)
            droide.setPos(cuadricula, pos)
            cuadricula[pos.first][pos.second] = droide
        }
    }

    fun getRandomDroide(porcentajes: IntArray = intArrayOf(30, 50, 20)): Droide{
        val values = arrayOf(
            0 until  porcentajes[0],
            porcentajes[0] until porcentajes[0] + porcentajes[1],
        )
        return when((0..100).random()){
            in values[0] -> SW348(50)
            in values[1] -> SW447(100)
            else -> SW4421((100..150).random())
        }
    }
}