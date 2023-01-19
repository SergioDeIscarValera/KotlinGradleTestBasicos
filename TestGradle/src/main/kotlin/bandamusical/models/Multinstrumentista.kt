package bandamusical.models

import bandamusical.interfeces.IBajista
import bandamusical.interfeces.IPercusionista
import bandamusical.interfeces.ITeclista

class Multinstrumentista(nombre:String,
                         override val experiencia: Int,
                         override val numCuerdas: Int,
                         override val percussion: PercussionType,
                         override val cantidadTeclados: Int): Musician(nombre), IBajista, ITeclista, IPercusionista {
    override val salario: Float = super.salario * 1.45f
}