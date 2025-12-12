package sintaxis

import java.time.LocalDateTime

fun main(){
    println("Cual es tu nombre")
    //Esperamos entrada por parte del usuario
    val nombre = readLine() ?: "Visitante"

    //Cogemos la librería de java.time la hora
    val horaActual =  LocalDateTime.now().hour

    //Creación de una función anónima
    val saludo = when {
        horaActual >= 18 -> "Buenas tardes"
        horaActual >= 12 -> "Buenos días"
        else -> "Buenas noches"
    }

    //Devolver resultados
    println("$saludo, $nombre")

}