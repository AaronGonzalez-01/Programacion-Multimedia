package sintaxis

fun main(){
    val numeros = listOf(1,2,3,4,5,6,7,8,9,10)

    //Números pares
    val pares = numeros.filter{it % 2 == 0 }
    println("Números pares: $pares")

    //Números pares multiplicados por dos
    val paresDobles = numeros.filter{it % 2 == 0}.map{it * 2 }
    println("Pares duplicados: $paresDobles")

    //Suma de números mayores sintaxis.a 5
    val sumasGrandes = numeros.filter { it > 5 }.sum()
    println("Suma de números mayores sintaxis.a 5: $sumasGrandes")

    //Verificar si hay números primos
    fun esPrimo(numero: Int): Boolean{
        if(numero <= 1) return false
        for (i in 2 until numero){
            if(numero % i == 0){
                return false
            }
        }
        return true
    }
    val primos = numeros.filter { esPrimo(it) }
    println("Primos: $primos")
}