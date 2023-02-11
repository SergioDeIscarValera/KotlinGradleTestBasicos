package roundRobin.models

class Prioridad(quantum: Float) : Cpu(quantum) {
    override fun enqueue(proceso: Proceso): Proceso {
        if (procesos.isEmpty()) {
            procesos.add(proceso)
            return proceso
        }

        for (i in procesos.indices) {
            if (proceso.prioridad < procesos[i].prioridad) {
                procesos.add(i, proceso)
                return proceso
            }
        }

        procesos.add(proceso)
        return proceso
    }
}