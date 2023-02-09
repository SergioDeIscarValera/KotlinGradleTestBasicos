package clase_gen.exceptions

sealed class ClaseExceptions(message: String) : Exception(message){
    class ClaseArgumentException(message: String) : ClaseExceptions(message)
    class ClaseInvalidException(message: String) : ClaseExceptions(message)
}
