package DAM_Zum

const val INPUT_REGEX = """^[1-4]{0,1}\d{0,2}(,|\.|${'$'})\d{0,2}${'$'}"""
/*
fun main(){
    val damZum = dam_zum()
    var saldoMio = 500.5f
    var saldoOtro = 500.5f
    do {
        println("Tu saldo: $saldoMio")
        println("Saldo del otro: $saldoOtro")
        if (damZum.menu()){
            val resultadoOp = damZum.enviar(damZum.inputCantidad(), saldoMio, saldoOtro)
            saldoMio -= resultadoOp - saldoOtro
            saldoOtro = resultadoOp
        }else{
            val resultadoOp = damZum.recibir(damZum.inputCantidad(),saldoOtro)
            saldoMio = resultadoOp
        }
    }while (saldoMio > 0)

}*/

class dam_zum{
    fun menu():Boolean{
        println("Quieres enviar o recibir? (enviar | recibir): ")
        do {
            val response = readln()
            if (response == "enviar")
                return true
            else if (response == "recibir")
                return false
            else println("Valor no valido (enviar | recibir): ")
        }while (true)
    }

    fun inputCantidad(): Float{
        println("Introduce la cantidad:")
        do {
            var message = readln()
            val regex = Regex(INPUT_REGEX)
            message = message.replace(",",".") // Problema al castear si tiene coma
            if (!regex.matches(message)) println("Error no es valido") else return message.toFloat()
        }while (true)
    }

    fun inputCantidad(message: String): Boolean {
        var message = message
        val regex = Regex(INPUT_REGEX)
        if (!regex.matches(message)) return false
        message = message.replace(",",".") // Problema al castear si tiene coma
        if (message.toDouble() < 0.5) return false
        return true
    }

    fun recibir(count: Float, origenSlado: Float,): Float {
        return operacion(count, origenSlado)
    }

    fun enviar(count: Float, origenSlado: Float, destinoSlado: Float): Float{
        if (origenSlado < count) return -1f
        return operacion(count, destinoSlado)
    }

    fun operacion(count: Float, destinoSlado: Float): Float {
        if (!inputCantidad(count.toString())) return -1f
        return destinoSlado + count
    }
}