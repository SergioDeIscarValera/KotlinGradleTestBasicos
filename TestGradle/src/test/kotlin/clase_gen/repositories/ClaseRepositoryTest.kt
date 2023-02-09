package clase_gen.repositories

import clase_gen.enums.Modulo
import clase_gen.factories.ClaseFactory
import clase_gen.models.Alumno
import clase_gen.models.Persona
import clase_gen.models.Profesor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClaseRepositoryTest {
    private lateinit var repository: ClaseRepository

    private val clase: Array<Persona> = arrayOf(
        Alumno(
            UUID.fromString("ee031a69-f19a-4cff-bdd3-f81d7f1dc23d"),
            "Juan", 30, 7.0f
        ),
        Alumno(
            UUID.fromString("29dd6206-a589-445c-929d-ef5fe5f3e8bf"),
            "Pedro", 22, 8.0f
        ),
        Alumno(
            UUID.fromString("6eada70b-7e6e-4840-996a-3d859b95f0fa"),
            "Maria", 21, 9.0f
        ),
        Alumno(
            UUID.fromString("04cc2f04-a7b5-11ed-afa1-0242ac120002"),
            "Jose", 23, 10.0f
        ),
        Profesor(
            UUID.fromString("1646ede6-a7b5-11ed-afa1-0242ac120002"),
            "Pepe", 40, Modulo.PROGRAMACION
        )
    )

    @BeforeEach
    fun setUp() {
        repository = ClaseRepository()
        repository.saveAll(clase)
    }

    @Test
    fun getAll(){
        val clase = repository.getAll()
        assertAll(
            { assertEquals("Juan", clase[0]?.nombre) },
            { assertEquals("Pedro", clase[1]?.nombre) },
            { assertEquals("Maria", clase[2]?.nombre) },
            { assertEquals("Jose", clase[3]?.nombre) },
            { assertEquals("Pepe", clase[4]?.nombre) }
        )
    }

    @Test
    fun find(){
        val search = repository.find(UUID.fromString("ee031a69-f19a-4cff-bdd3-f81d7f1dc23d"))
        val searchNull = repository.find(UUID.fromString("70d1528f-ecbd-45de-bc0f-7218fbafd03a"))
        assertAll(
            { assertEquals("Juan", search?.nombre) },
            { assertNull(searchNull) },
        )
    }

    @Test
    fun add(){
        val alumno = Alumno(
            UUID.fromString("70d1528f-ecbd-45de-bc0f-7218fbafd03a"),
            "Luis", 24, 6.0f
        )
        assertAll(
            { assertEquals("Luis", repository.add(alumno)?.nombre) }
        )
    }

    @Test
    fun addMax(){
        repository.saveAll(ClaseFactory.createClase(25))

        val alumno = Alumno(
            UUID.fromString("70d1528f-ecbd-45de-bc0f-7218fbafd03a"),
            "Luis", 24, 6.0f
        )
        assertAll(
            { assertNull(repository.add(alumno)) }
        )
    }

    @Test
    fun update(){
        val alumno = Alumno(
            UUID.fromString("ee031a69-f19a-4cff-bdd3-f81d7f1dc23d"),
            "Luis", 24, 6.0f
        )
        assertAll(
            { assertEquals(alumno.nombre, repository.update(alumno, clase[0].id)?.nombre) }
        )
    }

    @Test
    fun delete(){
        assertAll(
            { assertEquals(clase[0].nombre, repository.delete(clase[0].id)?.nombre) },
            { assertEquals(clase[4].nombre, repository.delete(clase[4].id)?.nombre) },
        )
    }

    @Test
    fun bubbleSort(){
        val clase = repository.bubbleSort()
        assertAll(
            { assertEquals("Maria", clase[0]?.nombre) },
            { assertEquals("Pedro", clase[1]?.nombre) },
            { assertEquals("Jose", clase[2]?.nombre) },
            { assertEquals("Juan", clase[3]?.nombre) },
            { assertEquals("Pepe", clase[4]?.nombre) }
        )
    }
}