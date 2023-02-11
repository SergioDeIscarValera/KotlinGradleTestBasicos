package supermercado

import supermercado.factories.ClientesFactory

fun main(){
    val clientes = ClientesFactory.crearClientesRdn()
    var contadorTotal = 0
    for (i in clientes){
        var contador = 0
        for (j in i.indices()){
            val precio = i.peek()?.precio ?: 0
            contador += precio
            contadorTotal += precio
            i.pop()
        }
        println("El cliente ${clientes.indexOf(i)+1} tiene un total de $contador")
    }
    println("\nEl total de todas las compras es $contadorTotal")
}