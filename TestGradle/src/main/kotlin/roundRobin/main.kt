package roundRobin

import Objetos.FootBall.inputNumber
import roundRobin.models.Cpu
import roundRobin.models.Prioridad
import roundRobin.models.Proceso
import roundRobin.models.RoundRobin

fun main(){
    val maxTime = (6..10).random()
    val cpu: Cpu = inputCPU()

    simulation(cpu, maxTime)
    println()
    cpu.unpackingFinalizados()
}

private fun simulation(cpu: Cpu, maxTime: Int) {
    var time = 0.0f
    do {
        if (time % 2 == 0f) {
            addProceso(cpu)
        }

        println(cpu.procesar())

        time += cpu.quantum
        time = (time * 100).toInt() / 100f
        Thread.sleep(150)
    } while (time < maxTime)
}

private fun addProceso(cpu: Cpu) {
    val proceso = Proceso((1..4).random().toFloat(), (0..3).random())
    println("Nuevo proceso -> $proceso")
    cpu.enqueue(proceso)
}

private fun inputCPU() = if (inputNumber("Round Robin (1) o Prioridad (2): ") == 1) {
    RoundRobin(0.2f)
} else {
    Prioridad(0.2f)
}
