package carrito

fun main(){
    /* Carrito:
        0 -> Nombre
        1 -> Precio Unitario
        2 -> Cantidad
        3 -> Precio Final (1*2)
        4 -> Iva (0 -> Sin IVA, 1 -> 10%, 2 -> 21%)
        5 -> Precio Con IVA
     */
    val carrito: Array<Array<Any>> = Array(10){ arrayOf("", 0.0, 0, 0.0, 0 ,0.0) }

    do {
        /*when(menu()){
            0 -> mostrarCarrito(carrito)
            1 -> añadirProducto(carrito)
            2 -> eliminarProducto(carrito)
            3 -> cambiarProducto(carrito)
            else -> break // Terminar
        }*/
    }while (numOfProductos(carrito) < 10)

}

fun mostrarCarrito(carrito: Array<Array<Any>>) {
    TODO("Not yet implemented")
}

fun menu(): Int {
    println("¿Qué hación quieres realizar?")
    println("| mostrar | añadir / add | eliminar / rm | cambiar | salir / q |")
    var response = -1
    do {
        val responseText = readln()
        when(responseText){
            "mostrar"   -> { response = 0; break }
            "añadir"    -> { response = 1; break }
            "add"       -> { response = 1; break }
            "eliminar"  -> { response = 2; break }
            "rm"        -> { response = 2; break }
            "cambiar"   -> { response = 3; break }
            "salir"     -> { response = 4; break }
            "q"         -> { response = 4; break }
        }
        println("\nIntroduce un valor valido:")
        println("| mostrar | añadir / add | eliminar / rm | cambiar / ch | salir / q |")
    }while (true)
    return response
}