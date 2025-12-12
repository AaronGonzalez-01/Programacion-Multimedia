package p01ejerciciosbasicos.opcionales

fun main(){

}

fun menuIMC(){
    while(true){
        println("*** IMC ***")
        println("1. Calcular IMC")
        println("2. Historial IMC")
        println("3. Tendencias IMC")
        println("4. Salir")
        print("Opción: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> ""
            2 -> ""
            3 -> ""
            4 -> break
            else -> println("Opción no válida")
        }
    }
}

fun calcularIMC(peso:Double, altura:Double):Double{
    return peso/(altura*altura)
}