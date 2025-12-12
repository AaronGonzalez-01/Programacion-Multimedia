package p01ejerciciosbasicos.opcionales

const val maxIntentos = 7
var record = 0

fun main(){
    menuJuego()
}

fun menuJuego(){
    while (true) {
        println("\n*** Adivina el número ***")
        println("Número de juegos ganados esta sesión: $record")
        println("1. Jugar")
        println("2. Informacion")
        println("3. Salir")
        print("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> jugar()
            2 -> mostrarInformacion()
            3 -> break
            else -> println("Opción inválida")
        }
    }
}

fun mostrarInformacion(){
    println("Tienes que adivinar un número del 1 al 100, tienes 7 intentos")
}

fun jugar(){
    val numeroGanador = (1..100).random()
    var intentos = maxIntentos
    var estado = false
    while (true) {
        println("Introduce un número entre 1 y 100")
        println("Intentos restantes: $intentos")

        val numeroIntroducido = readlnOrNull()?.toIntOrNull()

        while (intentos > -1) {
            if (numeroIntroducido == null) {
                println("Introduce un número")
            }

            if (numeroGanador == numeroIntroducido) {
                println("Haz ganado!!!")
                record++
                estado = true
                break
            } else {
                println("Número equivocado")
                arribaAbajo(numeroIntroducido, numeroGanador)
                intentos--
            }

            if (intentos == 0) {
                println("GAME OVER")
                estado = true
                break
            } else {
                println("Intentalo otra vez")
                break
            }
        }

        if (estado) {
            break
        }

    }
}

fun arribaAbajo (numeroIntroducido: Int?, numeroGanador: Int) {
    if (numeroIntroducido != null) {
        if (numeroIntroducido > numeroGanador) {
            println("El número ganador esta por abajo")
        }else{
            println("El número ganador esta por arriba")
        }
    }
}
