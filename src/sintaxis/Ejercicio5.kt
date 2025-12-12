package sintaxis

//Creación del objeto sintaxis.Usuario
data class Usuario(val nombre: String, val email: String, val edad: Int)

//Función que valida el nombre del sintaxis.Usuario
fun validarNombre(nombre: String): Result<String> {
    return if(nombre.length < 3){
        Result.failure(Exception("El nombre debe de tener al menos 3 caracteres"))
    }else {
        Result.success(nombre)
    }
}

fun validarEmail(email: String): Result<String> {
    return if(email.contains("@") && email.contains(".")) {
        Result.success(email)
    }else {
        Result.failure(Exception("El email no es valido"))
    }
}

fun validarEdad(edad: Int): Result<String> {
    return if(edad in 18..120) {
        Result.success(edad.toString())
    }else {
        Result.failure(Exception("La edad no es valida, tiene que ser entre 18 y 120"))
    }
}

//Ejecución del código
fun main(){
    //Petición al usuario para que se registre
    println("Nombre: ")
    val nombre = readLine() ?: ""

    println("Email: ")
    val email = readLine() ?: ""

    println("Edad: ")
    val edad = readLine()?.toIntOrNull() ?: -1

    //Validación de campos
    val resulatadoNombre = validarNombre(nombre)
    val resultadoEmail = validarEmail(email)
    val resultadoEdad = validarEdad(edad)

    //Devolución de resultados y errores
    when {
        resulatadoNombre.isFailure -> println("Error en el nombre: ${resulatadoNombre.exceptionOrNull()?.message}")
        resultadoEmail.isFailure -> println("Error en el email: ${resultadoEmail.exceptionOrNull()?.message}")
        resultadoEdad.isFailure -> println("Error en la edad: ${resultadoEdad.exceptionOrNull()?.message}")
        else -> {
            //Inicializamos el usuario tras hacer las verificaciones
            val usuario = Usuario(nombre, email, edad)
            println("sintaxis.Usuario credado: ${usuario.nombre}")
        }
    }

}