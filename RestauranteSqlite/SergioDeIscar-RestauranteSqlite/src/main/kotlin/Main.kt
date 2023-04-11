import controllers.IngredienteController
import repositories.ingrediente.IngredienteRepositoryMap
import services.storage.ingrediente.IngredienteDB
import services.storage.ingrediente.IngredienteFileCsv

fun main(){
    val controllerCsv = IngredienteController(
        IngredienteRepositoryMap(
            IngredienteFileCsv
        )
    )
    val ingredientesCsv = controllerCsv.getAll()
    val controllerDB = IngredienteController(
        IngredienteRepositoryMap(
            IngredienteDB
        )
    )
    controllerDB.saveAll(ingredientesCsv)
    println("Tienen el mismo contendio: ${ingredientesCsv == controllerDB.getAll()}")
}