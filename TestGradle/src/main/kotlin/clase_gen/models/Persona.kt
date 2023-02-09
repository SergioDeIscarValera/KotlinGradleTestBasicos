package clase_gen.models

import java.util.UUID

abstract class Persona(var id: UUID = UUID.randomUUID(), val nombre: String, val edad: Int)