package roundRobin.models

class Proceso(var tiempo: Float, val prioridad: Int) {
    private var id = count++
    companion object {
        var count = 0
    }

    override fun toString(): String {
        return "P$id -> T: $tiempo | P: $prioridad"
    }
}