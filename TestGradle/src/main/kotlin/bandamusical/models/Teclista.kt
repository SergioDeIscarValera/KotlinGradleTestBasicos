package bandamusical.models

import bandamusical.interfeces.ITeclista

class Teclista(nombre:String,
               override val experiencia: Int,
               override val cantidadTeclados: Int): Musician(nombre), ITeclista {
    override val salario: Float = super.salario * 1.3f
}