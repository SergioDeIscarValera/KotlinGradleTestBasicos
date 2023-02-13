package parkingv2.controllers

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import parkingv2.enums.Marcas
import parkingv2.interfaces.IParkingRepository
import parkingv2.models.Camioneta
import parkingv2.models.Coche
import parkingv2.models.Moto
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ParkingControllerTest {
    @MockK
    private lateinit var repository: IParkingRepository

    @InjectMockKs
    private lateinit var controller: ParkingController

    private val vehiculos = arrayOf(
        Coche("1817KCF", Marcas.CITROEN, "test", 2000, 5),
        Coche("3938BXQ", Marcas.CITROEN, "test", 2010, 5),
        Moto("7222CET", Marcas.CITROEN, "test", 2007, 125),
        Camioneta("7314NGD", Marcas.CITROEN, "test", 2021, 2500),
    )

    val vehiculo = Moto("2927MTE", Marcas.PEUGEOT, "test", 2000, 600)
    val vehiculoBadYear = Moto("AAAAAAA", Marcas.PEUGEOT, "test", 1800, 600)
    val vehiculoBadMatricula = Moto("AAAAAAA", Marcas.PEUGEOT, "test", 2000, 1100)

    @Test
    fun estaCompleto_Test(){
        every { repository.estaCompleto() } returns false
        assertFalse { controller.estaCompleto() }
        verify { repository.estaCompleto() }
    }

    @Test
    fun listadoOrdenadoPorMatricula_Test(){
        every { repository.listadoOrdenadoPorMatricula() } returns vehiculos
        assertContentEquals(vehiculos, controller.listadoOrdenadoPorMatricula())
        verify { repository.listadoOrdenadoPorMatricula() }
    }

    @Test
    fun listadoOrdenadoPorMatriculacionDesc_Test(){
        every { repository.listadoOrdenadoPorMatriculacionDesc() } returns vehiculos
        assertContentEquals(vehiculos, controller.listadoOrdenadoPorMatriculacionDesc())
        verify { repository.listadoOrdenadoPorMatriculacionDesc() }
    }

    @Test
    fun getAll_Test(){
        every { repository.getAll() } returns vehiculos
        assertContentEquals(vehiculos, controller.getAll())
        verify { repository.getAll() }
    }

    @Test
    fun add_Test(){
        every { repository.estaCompleto() } returns false
        every { repository.find(vehiculos[0].matricula) } returns vehiculos[0]
        every { repository.find(vehiculoBadMatricula.matricula) } returns null
        every { repository.find(vehiculo.matricula) } returns null
        every { repository.add(vehiculo) } returns vehiculo

        assertAll(
            {
                assertThrows<IllegalArgumentException>("El vehiculo ya esta en el parking"){
                    controller.add(vehiculos[0])
                }
            },
            {
                assertThrows<IllegalArgumentException>("Matricula o año de matriculacion incorrectos"){
                    controller.add(vehiculoBadYear)
                }
            },
            {
                assertThrows<IllegalArgumentException>("Matricula incorrecta"){
                    controller.add(vehiculoBadMatricula)
                }
            },
            { assertEquals(vehiculo, controller.add(vehiculo)) }
        )

        verify { repository.estaCompleto() }
        verify { repository.find(vehiculos[0].matricula) }
        verify { repository.find(vehiculo.matricula) }
        verify { repository.find(vehiculoBadMatricula.matricula) }
        verify { repository.add(vehiculo) }
    }

    @Test
    fun update_Test(){
        every { repository.find(vehiculo.matricula) } returns null
        every { repository.find(vehiculos[0].matricula) } returns vehiculos[0]
        every { repository.update(vehiculo, vehiculos[0].matricula) } returns vehiculo

        assertAll(
            {
                assertThrows<IllegalArgumentException>("Matricula o año de matriculacion incorrectos"){
                    controller.update(vehiculoBadYear, vehiculos[0].matricula)
                }
            },
            {
                assertThrows<IllegalArgumentException>("Matricula o año de matriculacion incorrectos"){
                    controller.update(vehiculoBadMatricula, vehiculos[0].matricula)
                }
            },

            {
                assertThrows<IllegalArgumentException>("Matricula incorrecta"){
                    controller.update(vehiculo, vehiculoBadMatricula.matricula)
                }
            },

            {
                assertThrows<IllegalArgumentException>("El vehiculo no esta en el parking"){
                    controller.update(vehiculo, vehiculo.matricula)
                }
            },

            { assertEquals(vehiculo, controller.update(vehiculo, vehiculos[0].matricula)) }
        )

        verify { repository.update(vehiculo, vehiculos[0].matricula) }
        verify { repository.find(vehiculo.matricula) }
        verify { repository.find(vehiculos[0].matricula) }
    }

    @Test
    fun delete_Test(){
        every { repository.find(vehiculo.matricula) } returns null
        every { repository.find(vehiculos[0].matricula) } returns vehiculos[0]
        every { repository.delete(vehiculos[0].matricula) } returns vehiculos[0]

        assertAll(
            {
                assertThrows<IllegalArgumentException>("Matricula incorrecta"){
                    controller.delete(vehiculoBadMatricula.matricula)
                }
            },
            {
                assertThrows<IllegalArgumentException>("El vehiculo no esta en el parking"){
                    controller.delete(vehiculo.matricula)
                }
            },
            { assertEquals(vehiculos[0], controller.delete(vehiculos[0].matricula)) }
        )

        verify { repository.find(vehiculo.matricula) }
        verify { repository.find(vehiculos[0].matricula) }
        verify { repository.delete(vehiculos[0].matricula) }
    }
}