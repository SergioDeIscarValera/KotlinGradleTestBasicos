package clone_wars

import clone_wars.factory.DroideFactory.randomDroides
import clone_wars.models.Droide
import clone_wars.models.SW348
import clone_wars.models.SW447

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED_BACKGROUND = "\u001B[41m"
const val ANSI_WHITE = "\u001B[37m"
const val ANSI_BLUE_BACKGROUND = "\u001B[44m"

fun main(){
    val size = inputNumber("Introduce el tamaño de la cuadricula:", 5..9)
    val cuadricula = Array(size){Array<Droide?>(size){null} }
    val countDroides = (5..20).random()
    randomDroides(cuadricula, countDroides)

    // Simulador
    val maxTime = (1000..3000).random()

    val informe = IntArray(4) // 0 -> Droides iniciales, 1 -> disparos, 2 -> aciertos, 3 -> droides muertos
    informe[0] = countDroides

    var time = 0
    var hitsPos: Array<Pair<Int,Int>>
    do {
        val rdnDroide = getRandomDroidePos(cuadricula, countDroides - informe[3])
        hitsPos = disparoAllCalculos(cuadricula, rdnDroide, informe)

        mostrarCuadricula(cuadricula, hitsPos, rdnDroide.getPosDroide())

        time += 100;

        // Recolocar
        if (time % 300 == 0){
            recolocarDroides(cuadricula)
        }

        Thread.sleep(100)
    }while (time < maxTime)

    mostrarInforme(informe, cuadricula)
}

fun mostrarInforme(informe: IntArray, cuadricula: Array<Array<Droide?>>) {
    println("Informe:")
    println("Número total de disparos: ${informe[1]}")
    println("Número total de aciertos: ${informe[2]}")
    println("Porcentaje de acierto: ${informe[2] * 100 / informe[1]}%")
    println("Droides iniciales: ${informe[0]}")
    println("Droides finales: ${informe[0] - informe[3]}")
    println("Droides muertos: ${informe[3]}")
    println()
    println(sortDroides(cuadricula, informe[0] - informe[3]))
}

fun sortDroides(cuadricula: Array<Array<Droide?>>, count: Int): String {
    val droides = Array<Droide?>(count){null}
    var index = 0
    for (i in cuadricula){
        for (j in i){
            if (j != null){
                droides[index] = j
                index++
            }
        }
    }

    // Ordenar por escudo, burbuja
    for (i in droides.indices){
        for (j in 0 until droides.size - 1){
            if (droides[j]!!.getEscudo() < droides[j + 1]!!.getEscudo()){
                val aux = droides[j]
                droides[j] = droides[j + 1]
                droides[j + 1] = aux
            }
        }
    }
    val result = StringBuilder()
    result.append("Droides restantes:\n")
    for (i in droides){
        //Almacenar su clase y su escudo
        result.append("\t${i!!.javaClass.simpleName}:\t${i.getEscudo()}\n")
    }
    return result.toString()
}

private fun recolocarDroides(cuadricula: Array<Array<Droide?>>) {
    val oldCuadricula = Array(cuadricula.size){Array<Droide?>(cuadricula[0].size){null} }
    for (i in cuadricula.indices){
        for (j in cuadricula[i].indices){
            oldCuadricula[i][j] = cuadricula[i][j]
        }
    }

    for (i in cuadricula.indices){
        for (j in cuadricula[i].indices){
            cuadricula[i][j] = null
        }
    }
    for (i in oldCuadricula){
        for (j in i){
            if (j == null) continue
            var index: Pair<Int,Int>
            do {
                index = Pair(cuadricula.indices.random(), cuadricula[0].indices.random())
            }while (cuadricula[index.first][index.second] != null)
            cuadricula[index.first][index.second] = j
        }
    }
}

private fun disparoAllCalculos(cuadricula: Array<Array<Droide?>>, rdnDroide: Droide, informe: IntArray): Array<Pair<Int,Int>> {
    val result = rdnDroide.disparar(cuadricula)
    if (result.first) {
        println("DISPARO!!!! ${rdnDroide.getPosDroide()}")
        checkDead(cuadricula, result.second, informe)
        informe[2]++
    } else {
        println("No le ha dado a nada ${rdnDroide.getPosDroide()}")
    }
    informe[1]++
    return result.second
}

private fun checkDead(cuadricula: Array<Array<Droide?>>, hitsPos:  Array<Pair<Int, Int>>, informe: IntArray) {
    for (i in hitsPos){
        if (checkPos(cuadricula, i) && cuadricula[i.first][i.second] != null && cuadricula[i.first][i.second]?.getEscudo() == 0){
            cuadricula[i.first][i.second] = null
            informe[3]++
        }
    }
}

private fun checkPos(cuadricula: Array<Array<Droide?>>, pos: Pair<Int, Int>): Boolean{
    return pos.first in cuadricula.indices && pos.second in cuadricula[0].indices
}

private fun getRandomDroidePos(cuadricula: Array<Array<Droide?>>, countDroides: Int): Droide{
    val stop = (0 until countDroides).random()
    var count = 0
    for (i in cuadricula){
        for(j in i){
            if (j != null){
                if (count == stop){
                    return j
                }
                else
                    count++
            }
        }
    }
    throw Exception("Error -> No ha encontrado un droide random.")
}

private fun mostrarCuadricula(cuadricula: Array<Array<Droide?>>, hitsPos: Array<Pair<Int,Int>>, hiterPos: Pair<Int,Int>){
    for (i in cuadricula.indices){
        for(j in cuadricula[i].indices){
            val message = StringBuilder()
            if (Pair(i,j) == hiterPos){
                message.append(ANSI_BLUE_BACKGROUND)
            }else{
                for (n in hitsPos){
                    if (Pair(i,j) == n)
                        message.append(ANSI_RED_BACKGROUND).append(ANSI_WHITE)
                }
            }
            message.append(when(cuadricula[i][j]){
                null -> "|\t\t_\t\t|"
                is SW348 -> "|\t348 (${cuadricula[i][j]?.getEscudo()})\t|"
                is SW447 -> "|\t447 (${cuadricula[i][j]?.getEscudo()})\t|"
                else -> "|\t442 (${cuadricula[i][j]?.getEscudo()})\t|"
            })
            message.append(ANSI_RESET)
            print(message)
        }
        println()
    }
    repeat(3){
        println()
    }
}

private fun inputNumber(message:String, range: IntRange = -999..999): Int {
    println(message)
    var response: Int
    do {
        response = readln().toIntOrNull() ?: Int.MIN_VALUE
        if (response in range)
            break
        else println("Error: Introduce un número valido, entre ${range.first}-${range.last}")
    }while (true)
    return response
}