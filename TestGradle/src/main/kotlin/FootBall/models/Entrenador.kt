package Objetos.FootBall.models

data class Entrenador(val nombre: String, val experience: Int){
    val id = Entrenador.nextId()
    // static:
    companion object{
        var count = 0
        private fun nextId():Int{
            return count++
        }
    }
}