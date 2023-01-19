package bandamusical.models

import bandamusical.interfeces.IGuitarrista

class Guitarrista(nombre:String,
                  override val experiencia: Int,
                  override val guitarra: GuitarraType): Musician(nombre), IGuitarrista {
    override val salario: Float = super.salario * 1.35f
}

enum class GuitarraType{
    CLASICA, //CLÁSICA
    ACUSTICA, //ACÚSTICA
    FLAMENCA,
    ELECTRICA, //ELÉCTRICA
    ELECTROACUSTICA, //ELECTROACÚSTICA
    SEMIACUSTICA, //SEMIACÚSTICA
    MIDI,
    ITALIANA
}