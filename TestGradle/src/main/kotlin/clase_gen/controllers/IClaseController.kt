package clase_gen.controllers

import clase_gen.models.Persona
import java.util.*

interface IClaseController: IController<Persona, UUID>{
    fun bubbleSort(): Array<Persona?>
}