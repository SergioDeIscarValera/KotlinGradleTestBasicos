package roundRobin.models

import roundRobin.interfaces.ICola

abstract class Cpu(val quantum: Float): ICola<Proceso> {

    protected val procesos: MutableList<Proceso> = mutableListOf()
    private val pilaFinalizados = Pila<Proceso>()

    fun procesar(): String{
        if (procesos.isEmpty()) return "idle"

        val proceso = procesos[0]

        proceso.tiempo -= quantum
        proceso.tiempo = (proceso.tiempo * 100).toInt() / 100f

        dequeue()

        return if (proceso.tiempo <= 0) {
            pilaFinalizados.push(proceso)
            "Finalizado $proceso"
        } else {
            enqueue(proceso)
            "Procesando $proceso"
        }
    }

    override fun dequeue(): Proceso? {
        if (procesos.isEmpty()) return null
        val proceso = procesos[0]
        procesos.removeFirst()
        return proceso
    }

    override fun first(): Proceso? {
        if (procesos.isEmpty()) return null
        return procesos.first()
    }

    override fun isEmpty(): Boolean {
        return procesos.isEmpty()
    }

    fun unpackingFinalizados() {
        println("Procesos finalizados:")
        pilaFinalizados.unpacking()
    }
}