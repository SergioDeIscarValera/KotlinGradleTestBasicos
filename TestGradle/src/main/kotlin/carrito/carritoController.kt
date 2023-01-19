package carrito

import kotlin.math.roundToInt

// Esto con una clase se haría mucho mejor
val productosNombres = arrayOf("Lápiz", "Manzana", "Ratón")
val productosPrecio = arrayOf(0.25, 1.0, 2.5)
val productosIva = arrayOf(0, 1, 2)

// Añada un producto a la primera posición libre, calculado los precios
fun addProducto(carrito: Array<Array<Any>>, nombre: String, cantidad: Int): Boolean {
    if ( !productoValido(nombre) ) return false
    if ( cantidad <= 0 ) return false
    val productoIndex = productoIndex(nombre)
    addProductoIndex(carrito, nombre, cantidad, productoIndex, carritoLastIndex(carrito) )
    return true
}

private fun addProductoIndex(carrito: Array<Array<Any>>, nombre: String, cantidad: Int, productoIndex: Int, indexCarrito: Int) {
    val total = (productosPrecio[productoIndex] * cantidad * 100.0).roundToInt() / 100.0
    val totalIva = when (productosIva[productoIndex]) {
        1 -> ((total + (total * 0.1)) * 100.0).roundToInt() / 100.0
        2 -> ((total + (total * 0.21)) * 100.0).roundToInt() / 100.0
        else -> total
    }
    carrito[indexCarrito] = arrayOf(nombre, productosPrecio[productoIndex], cantidad, total, productosIva[productoIndex], totalIva)
}

fun eliminarProducto(carrito: Array<Array<Any>>, index: Int): Boolean {
    if (index !in carrito.indices) return false
    if (carrito[index][0] == "") return false
    carrito[index] = arrayOf("", 0.0, 0, 0.0, 0 ,0.0)
    return true
}

fun cambiarProducto(carrito: Array<Array<Any>>, index: Int, newNombre: String, cantidad: Int): Boolean {
    if (index !in carrito.indices) return false
    if (!productoValido(newNombre)) return false
    if (cantidad <= 0) return false
    if (!eliminarProducto(carrito, index)) return false
    addProductoIndex(carrito, newNombre, cantidad, productoIndex(newNombre), index)
    return true
}

// Retorna el index buscando en la lista de productos
fun productoIndex(nombre: String): Int {
    for (i in productosNombres.indices){
        if (productosNombres[i] == nombre) return i
    }
    return -1
}

// Retorna la primera posición libre del carrito
fun carritoLastIndex(carrito: Array<Array<Any>>): Int {
    for (i in carrito.indices){
        if (carrito[i][0] == "") return i
    }
    return -1
}

// Retorna si el producto está en la lista de productos
fun productoValido(nombre: String): Boolean {
    for (i in productosNombres){
        if (i == nombre){
            return true
        }
    }
    return false
}

// Retorna la cantidad de productos que hay en el carrito
fun numOfProductos(carrito: Array<Array<Any>>): Int {
    var count = 0
    for (i in carrito){
        if (i[0].toString().isNotEmpty()){
            count++
        }else{
            break
        }
    }
    return count
}