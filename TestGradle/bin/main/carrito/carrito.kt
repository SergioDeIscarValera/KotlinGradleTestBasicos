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
        when(menu()){
            0 -> mostrarCarrito(carrito)
            1 -> addProducto(carrito, inputProducto(), inputCantidad())
            2 -> eliminarProducto(carrito, inputIndex(carrito.indices))
            3 -> cambiarProducto(carrito, inputIndex(carrito.indices), inputProducto(), inputCantidad())
            else -> break // Terminar
        }
    }while (numOfProductos(carrito) < 10)

    mostrarCarrito(carrito)
}

fun inputIndex(indices: IntRange): Int {
    println("Introduce la posicion del carrito ha modificar:")
    var response = -1
    do {
        response = readln().toIntOrNull() ?: -1
        if (response in indices.first+1..indices.last+1) break else println("Valor no valido ha de estar entre")
    }while (true)
    response--
    return response
}

fun inputCantidad(): Int {
    println("Introduce la cantidad de producto que quieres:")
    println("| Min: 1 | MAX: 25 |")
    val regex = Regex("""^\d(\d${'$'}|${'$'})""")
    var response: String
    do {
        response = readln()
        if (regex.matches(response) && response.toInt() >= 1 && response.toInt() <= 25) break else println("Valor no valido")
    }while (true)
    return response.toInt()
}

fun inputProducto(): String {
    println("Introduce el nombre de un producto:")
    println("| ${productosNombres.joinToString(" | ")} |")
    var response: String
    do {
        response = readln()
        if (!productoValido(response)) println("Valor no valido")
    }while (!productoValido(response))
    return response
}

fun mostrarCarrito(carrito: Array<Array<Any>>) {
    println()
    println("|\tNombre\t|\t€/Und\t\t|\tCant\t|\tTotal\t|\tIVA\t|\tTotal+IVA\t|")
    for (i in carrito){
        println("|\t${if (i[0] != ""){
            "${i[0]}"
        }else{
            "\t"
        }}\t|\t${i[1]}€\t\t|\t${i[2]}\t\t|\t${i[3]}€\t|\t${when(i[4]){
            0 -> "0%"
            1 -> "10%"
            2 -> "21%"
            else -> ""
        }}\t|\t${i[5]}€\t\t|")
    }
    var totalNoIVA = 0.0
    var totalIVA = 0.0

    for (i in carrito){
        totalNoIVA += i[3].toString().toDouble()
        totalIVA += i[5].toString().toDouble()
    }

    println("|\tTotal sin IVA: $totalNoIVA€\t|")
    println("|\tTotal con IVA: $totalIVA€\t|")

    println()
}

fun menu(): Int {
    println()
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
    println()
    return response
}