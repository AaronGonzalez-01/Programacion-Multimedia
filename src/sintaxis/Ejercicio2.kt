package sintaxis

class Ejercicio2 {

}

fun main(){
    println("*** Calculadora ***")

    //Pedimos el sintaxis.primer número
    print("Primer número: ")
    val a = readLine()?.toDoubleOrNull() ?: return

    //Pedimos la operación
    println("Operación +, -, *, /")
    val operacion = readLine() ?: return

    //Pedimos el segundo número
    print("Segundo número: ")
    val b = readLine()?.toDoubleOrNull() ?: return

    //Calcular el sintaxis.resultado
    val resultado = when (operacion){
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if(b != 0.0) a / b else { println("Error: divición por cero"); return}
        else -> {println ("Operación desconocida"); return}
    }

    //Imprimir sintaxis.resultado
    println(resultado)
}