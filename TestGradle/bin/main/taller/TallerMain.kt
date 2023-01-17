package taller

import taller.factories.PersonaFactory
import taller.models.*

fun main(){
    val personas = PersonaFactory.getInstance().getRandomTallerCompleto()
    println("---------------------")
    println("Simulador de taller con 50 trabajadores:")
    println("---------------------")
    println("Número de personas: ${countType<Persona>(personas)}")
    println("Número de jefes: ${countType<JefeTaller>(personas)}")
    println("Número de trabajadores: ${countType<Trabajador>(personas)}")
    println("Número de chapistas: ${countType<Chapista>(personas)}")
    println("Número de electricistas: ${countType<Electricista>(personas)}")
    println("---------------------")
    println("Nomina total del taller ${calculateNomina(personas)}€")
    println("---------------------")
    val primerJefe = findFirstType<JefeTaller>(personas) as JefeTaller
    primerJefe.darLatigazo(primerJefe.getTrabajador(0)!!)
    println("---------------------")
    (findFirstType<Chapista>(personas) as Chapista).arreglarChapa()
    println("---------------------")
    (findFirstType<Electricista>(personas) as Electricista).comer()
    println("---------------------")
    (findFirstType<Chapista>(personas) as Chapista).comer()
}

fun calculateNomina(personas: Array<Persona?>,
                    salarioJefe: Int = 2500,
                    salarioChapista: Int = 1700,
                    salarioElectricista: Int = 1800,): Int {
    var total = 0
    for (i in personas){
        total += when(i){
            is JefeTaller -> salarioJefe
            is Chapista -> salarioChapista
            is Electricista -> salarioElectricista
            else -> 0
        }
    }
    return total
}

// Muy parecido a C#
inline fun <reified Type : Persona> countType(personas: Array<Persona?>): Int {
    var count = 0
    for (i in personas){
        if (i is Type) count++
    }
    return count
}

inline fun <reified Type : Persona> findFirstType(personas: Array<Persona?>): Persona? {
    for (i in personas.indices){
        if (personas[i] is Type)
            return personas[i]
    }
    return null
}

