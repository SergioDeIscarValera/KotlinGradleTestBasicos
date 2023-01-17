package factura

import Objetos.FootBall.inputNumber
import Objetos.FootBall.inputString
import factura.factories.ProductoFactory
import factura.models.Factura
import factura.models.ItemFactura
import factura.models.Producto
import java.time.LocalDate
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = runBlocking {
    val productos = Array((1..15).random()){ Producto("name", 1f, 1) }
    ProductoFactory.getInstance().createProductoRandom(productos)
    println(mostrarProductos(productos))

    val nombreUsuario = inputString("Introduce el nombre del propietario de la compra:")

    //Coroutine
    val simMercado = launch {
        simularMercado(productos, 1000)
    }

    val factura = Factura(nombreUsuario, LocalDate.now())

    /*do {
        when(menu()){
            1 -> {
                println(factura)
                delay(2500)
            }
            2 -> async {addItemAlCarro(factura, productos)}.await()
            3 -> println()//factura.deleteItem()
            4 -> println()//factura.updateItem()
            5 -> println()//factura.findItem()
            else -> break
        }
    }while (true)*/

    println(factura)

    simMercado.join()
}
private fun addItemAlCarro(factura: Factura, opciones: Array<Producto>){
    val producto = inputProducto(opciones)
    if (producto == null){
        printError("No se ha encontrado el producto")
        return
    }
    val cantidad = inputNumber("Introduce la cantidad de producto que quieres comprar: ", 1..producto.stock)

    val index = opciones.indexOf(producto)

    val item = ItemFactura(producto, cantidad)
    factura.addItem(item)

    opciones[index].SetStock(opciones[index].stock - cantidad)

    println("Se ha añadido:\n\t$item")
}

fun mostrarProductos(productos: Array<Producto>): String {
    val stringBuilder = StringBuilder("Lista de productos disponibles:")
    for (i in productos.indices){
        stringBuilder.append("\n\t${i+1}-> ").append(productos[i])
    }
    return stringBuilder.toString()
}

private fun menu(): Int{
    return inputNumber("¿Qué quieres hacer?\n" +
            "1->\tMostrar carro\n" +
            "2->\tAñadir producto\n" +
            "3->\tBorrar producto\n" +
            "4->\tCambiar producto\n" +
            "5->\tBuscar producto\n" +
            "6->\tFinalizar compra\n",
        1..6)
}

private fun inputProducto(opciones: Array<Producto>): Producto?{

    if (inputNumber(mostrarProductos(opciones) + "\n\n\n" + "¿Como quieres seleccionar el producto?\n" +
                "1->\tSeleccionar por nombre\n" +
                "2->\tSeleccionar por número de lista", 1..2) == 1)
        return inputProductoByName(opciones)
    return inputProductoByIndex(opciones)
}

private fun inputProductoByName(opciones: Array<Producto>): Producto?{
    val nombreFind = inputString(mostrarProductos(opciones) + "\n\n\n" + "Introduce el nombre del producto que vas a añadir:")
    for (i in opciones){
        if (i.nombre.lowercase().trim() == nombreFind.lowercase().trim())
            return i
    }
    return null
}

private fun inputProductoByIndex(opciones: Array<Producto>): Producto{
    return opciones[inputNumber(mostrarProductos(opciones) + "\n\n\n" + "Introduce el número del producto que vas a añadir:", 1..opciones.size) - 1]
}

private fun printError(error: String){
    println("Error: $error")
    Thread.sleep(1500)
}

suspend fun simularMercado(productos: Array<Producto>, delay: Long = 5000, interactions: Int = 10) {
    var count:Long = 0
    val maxCount = delay * interactions
    do {
        delay(delay)
        for (i in productos.indices){
            productos[i].SetPrecio(ProductoFactory.getInstance().getPrecioRandom())
        }
        println("\n***El precio a cambiado***\n")
        count += delay
        println("Count = $count/${maxCount}")
    }while (true/*count < maxCount*/)
}