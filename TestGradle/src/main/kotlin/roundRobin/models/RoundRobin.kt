package roundRobin.models

class RoundRobin(quantum: Float): Cpu(quantum) {
    override fun enqueue(proceso: Proceso): Proceso {
        procesos.add(proceso)
        return proceso
    }
}