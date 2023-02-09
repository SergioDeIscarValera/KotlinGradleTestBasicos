package clase_gen.controllers

import clase_gen.enums.Modulo
import clase_gen.exceptions.ClaseExceptions
import clase_gen.models.*
import clase_gen.repositories.ClaseRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ClaseControllerTest {
    @MockK
    private lateinit var repository: ClaseRepository

    @InjectMockKs
    private lateinit var controller: ClaseController

    init {
        MockKAnnotations.init(this)
    }

    private val clase: Array<Persona?> = arrayOf(
        Alumno(
            UUID.fromString("ee031a69-f19a-4cff-bdd3-f81d7f1dc23d"),
            "Juan", 20, 7.0f
        ),
        Alumno(
            UUID.fromString("29dd6206-a589-445c-929d-ef5fe5f3e8bf"),
            "Pedro", 21, 8.0f
        ),
        Alumno(
            UUID.fromString("6eada70b-7e6e-4840-996a-3d859b95f0fa"),
            "Maria", 22, 9.0f
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

    @Test
    fun getAll() {
        every { repository.getAll() } returns clase

        val res = controller.getAll()
        assertAll(
            { assertNotNull(res) },
            { assert(res.size == clase.size) },
            { assertEquals(clase[0]?.nombre, res[0]?.nombre)},
            { assertEquals(clase[3]?.nombre, res[3]?.nombre)},
            { assertEquals(clase[4]?.nombre, res[4]?.nombre)}
        )

        verify { repository.getAll() }
    }

    @Test
    fun find(){
        every { repository.find(clase[0]!!.id) } returns clase[0]
        every { repository.find(clase[4]!!.id) } returns clase[4]

        val res = controller.find(clase[0]?.id ?: UUID.randomUUID())
        val res2 = controller.find(clase[4]?.id ?: UUID.randomUUID())
        assertAll(
            { assertNotNull(res) },
            { assertNotNull(res2) },
            { assertEquals(clase[0]?.nombre, res?.nombre) },
            { assertEquals(clase[4]?.nombre, res2?.nombre) },
        )

        verify { repository.find(clase[0]!!.id) }
    }

    @Test
    fun add(){
        every { repository.add(clase[0]!!) } returns clase[0]

        assertAll(
            { assertEquals(clase[0], controller.add(clase[0]!!)) },
            {
                assertThrows<ClaseExceptions.ClaseArgumentException> {
                controller.add(Alumno(UUID.fromString("2f913c6f-9cd4-4a17-bb40-11508d68b9a3"), "", 0, 0.0f)) }
            },
            {
                assertThrows<ClaseExceptions.ClaseArgumentException> {
                    controller.add(Alumno(UUID.fromString("cdfe9716-596f-46a2-a64e-b98f71f75b28"), "Pepe", -1, 0.0f)) }
            }
        )

        verify { repository.add(clase[0]!!) }
    }

    @Test
    fun update(){
        every { repository.update(clase[1]!!, clase[0]!!.id) } returns clase[1]

        assertAll(
            { assertEquals(clase[1], controller.update(clase[1]!!, clase[0]!!.id)) },
        )

        verify { repository.update(clase[1]!!, clase[0]!!.id) }
    }

    @Test
    fun delete(){
        every { repository.delete(clase[2]!!.id) } returns clase[2]

        assertAll(
            { assertEquals(clase[2], controller.delete(clase[2]!!.id)) },
        )

        verify { repository.delete(clase[2]!!.id) }
    }
}