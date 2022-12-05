package Objetos.FootBall.models

/**
 * Entrenador con un nombre y los años de experiencia
 *
 * @param nombre Nombre dek entrenador
 * @param experience Años de experiencia del entrenador
 */
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