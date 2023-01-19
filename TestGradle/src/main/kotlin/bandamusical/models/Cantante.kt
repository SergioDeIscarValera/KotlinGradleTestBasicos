package bandamusical.models

import bandamusical.interfeces.ICantante

class Cantante(nombre:String,
               override val experiencia: Int,
               override val tono: TonoType): Musician(nombre), ICantante {
    override val salario: Float = super.salario * 1.4f
    override fun respirar() {
        "El músico cantante $nombre respira."
    }
}

enum class TonoType{
    CONTRALTO,
    MEZZOSOPRANO,
    SOPRANO,
    BAJO,
    BARITONO, //BARÍTONO
    TENOR,
    CONTRATENOR
}