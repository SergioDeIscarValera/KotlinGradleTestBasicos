package Aparcamiento.models

data class Conductor(val nombre: String, val DNI: String){
    val id = Conductor.nextId()
    companion object{
        var contador = 0
        private fun nextId(): Int {
            return contador++
        }
    }

    fun toJsonString():String{
        return """{ "nombre":"$nombre", "DNI":"$DNI" }"""
    }
}
