package sintaxis

import kotlin.collections.iterator

//(Inmutables - no pueden cambiar)
//Declaraciones de variables con tipo explícito
val nombre1: String = "Maria"
val edad1: Int = 21
val altura1: Double = 1.75

//Declaración de variables con inferencia de tipos
val ciudad = "Madrid" //Se deduce que es String
val contador = 42 //Se deduce que es int
val contador2 = 42.42 //Se deduce que es double

//Valores constantes sintaxis.a nivel de clase
const val PI = 3.14

//(Mutables - pueden cambiar)
//Declaraciones de variables con tipo explícito
var nombre2: String = "Maria"
var edad2: Int = 21
var altura2: Double = 1.75

//Conversión explicita
val entero: Int = 42

//Tipos de String
val multilineas = """
    Primera linea
    Segunda linea
    Tercera linea
""".trimIndent()

val precentacion = "Me llamo $nombre1 y tengo $edad2 años"
val a = 5
val b = 3
val resultado = "El sintaxis.resultado de $a + $b es ${a + b}"
val lista = listOf(1,2,3,4,5)
val describcion = "La sintaxis.lista tiene ${lista.size} elementos"
val primer = "El sintaxis.primer elemento es ${lista.firstOrNull() ?: "vacia" }"

//(Explicita)Declaración de funciones con parámetro y valor de retorno
fun suma(a: Int, b: Int = 0): Int {
    return a + b
}

//(Implícita)Declaración de funciones con parámetro y valor de retorno
fun multiplica(a: Int, b: Int): Int = a * b

//Función sin retorno (void)
fun saludar(): Unit{
    println("Hola mundo")
}

//Nulleable vs no-nulleable (interrogación para permitir valores vacíos)
var nombre3: String? = "Maria"

//Función con cuerpo de expresión
fun obtenerPi(): Double = 3.14

//Función que permite varias veces el mismo tipo de parámetros
fun sumarTodo(vararg numeros: Int): Int{
    var suma = 0
    for (numero in numeros) {
        suma += numero
    }
    return suma
}

//Sentencia when
fun obtenerDia(numero: Int): String = when (numero) {
    1 -> "Lunes"
    2 -> "Martes"
    3 -> "Miercoles"
    4 -> "Jueves"
    5 -> "Viernes"
    6 -> "Sabado"
    7 -> "Domingo"
    else -> "Invalido"
}

fun obtenerEdad(numero: Int): String = when (numero) {
    in 0..12 -> "Niño"
    in 13..19 -> "Adolecente"
    in 20..59 -> "Adulto"
    in 6..90 -> "Adulto mayor"
    else -> "Invalido"
}

fun describir(objeto: Any): String = when (objeto) {
    is String -> "Cadena de texto $objeto"
    is Int -> "Números enteros $objeto"
    is Double -> "Números decimales $objeto"
    is Boolean -> "Boolean $objeto"
    else -> "Invalido"
}

//Sentencias when con expresiones complejas
fun procesarEstado(estado: String): String = when {
    estado.isEmpty() -> "Estado esta vacio"
    estado.length > 100 -> "Estado demasiado largo"
    estado.contains("ERROR") -> "Estado contiene ERROR"
    else -> "Estado valido $estado"
}

//Equivalente sintaxis.a public static void sintaxis.main en java
fun main(){
    //Reasignación de valores
    nombre2 = "Maria"
    edad2 = 24

    //Conversión de sintaxis.entero
    val decimal: Double = entero.toDouble()
    val texto : String = entero.toString()
    val boolean : Boolean = entero != 0

    nombre3 = null

    //Llamadas con diferentes conbinaciones
    suma(1)
    suma(2, 3)
    suma(a = 4, b = 3)

    //Uso de diferentes cantidades de argumentos

    var array = intArrayOf(10, 20, 30)

    println(sumarTodo())
    println(sumarTodo(1,2))
    println(sumarTodo(1,2,3,4,5))
    println(sumarTodo(*array))


    //Condicionales
    val edad = 21
    val nota = 8

    //If
    if (edad > 18){
        println("Eres mayor de edad")
    }else {
        println("NO eres mayor de edad")

    }

    //Else if
    if (nota >= 9){
        println("Sobresaliente")
    }else if(nota >= 7){
        println("Notable")
    }else if (nota >= 6){
        println("bien")
    }else if(nota >= 5){
        println("Suficiente")
    }else {
        println("Suspenso")
    }

    //If como expresión
    val resultado1 = if(edad >= 18) "Mayor" else "Menor"
    val resultado2 = if(nota >= 9) "Sobresaliente"
    else if(nota >= 7) "Notable"
    else if (nota >= 6) "Bien"
    else if(nota >= 5) "Suficiente"
    else "Suspenso"

    //Bucles for
    for (i in 1..5){
        println(i)
    }

    for (i in 1..<5){
        println(i)
    }

    for (i in 5 downTo 1){
        println(i)
    }

    for (i in 1..10 step 2){
        println(i)
    }

    val nombres = listOf("Maria", "Pedro")
    for (nombre in nombres){
        println(nombre)
    }

    val mapa = mapOf("es" to "Español", "en" to "English")
    for ((idioma, nombre) in mapa){
        println("$idioma: $nombre")
    }

}