package p01ejerciciosbasicos.opcionales

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Locale.getDefault

const val RUTA = "src/p01ejerciciosbasicos/resources/notas"
const val TXT = ".txt"

data class Nota(var nombre: String, var fechaCreacion: LocalDateTime, var texto: String, var importante: Boolean){
    override fun toString(): String {
        return "$nombre: ${fechaCreacion.format(DateTimeFormatter.ISO_DATE)}"
    }
}

var notas = mutableListOf<Nota>()

fun main(){
    menuNotas()
}

fun menuNotas(){
    val notasCargadas = cargarNotas(RUTA)
    if (notasCargadas.isEmpty())
        println("[WARNING] **** No hay notas creadas en: $RUTA")

    while (true) {
        println("\n*** Gestor de notas ***")
        println("1. Crear nota")
        println("2. Buscar nota")
        println("3. Marcar nota como importante")
        println("4. Mostrar notas disponibles (por fecha de creación)")
        println("5. Exportar nota")
        println("6. Salir")
        print("Opción: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> crearNota()
            2 -> buscarPorNombre()
            3 -> marcarImportante()
            4 -> mostrarNotas()
            5 -> exportarNota()
            6 -> break
            else -> println("Opción inválida")
        }
    }
}

fun cargarNotas(ruta: String): ArrayList<String> {
    val notasCargadas = ArrayList<String>()
    val carpeta = File(ruta)
    if (!carpeta.exists()){
        println("La carpeta no existe")
        return notasCargadas
    }

    if (!carpeta.isDirectory){
        println("La carpeta no es un directorio")
        return notasCargadas
    }

    val elementos = carpeta.listFiles()

    if (elementos == null || elementos.isEmpty()){
        println("La carpeta esta vacia")
        return notasCargadas
    }

    elementos.forEach { it ->
        if (it.isFile){
            notasCargadas.add(it.name)
            val reader = BufferedReader(FileReader(it))
            val texto = it.bufferedReader().use { it.readText() }
            reader.close()
            notas.add(Nota(it.name, LocalDateTime.now(), texto, false))
        }

    }
    println("Notas cargadas desde $RUTA")
    return notasCargadas
}

fun crearNota() {
    println("\n*** Crear nota ***")
    println("Introduzca el nombre de la nota")
    val nombre = readlnOrNull().toString()
    if (nombre.isEmpty()){
        println("El nombre no puede ser vacio")
        return
    }
    if (!notas.none { it.nombre.contains(nombre) }){
        println("Ese nombre ya esta siendo utilizado")
        return
    }
    println("¿Qué quieres anotar?")
    val texto = readlnOrNull().toString()
    var importante = false
    while (true) {
        println("¿Es importante? s/n")
        val importanteRespuesta = readlnOrNull()?.trim().toString().lowercase(getDefault())
        if (importanteRespuesta == "s"){
            importante = true
            break
        }else if(importanteRespuesta == "n"){
            break
        }else{
            println("Respuesta no válida")
        }
    }
    notas.add(Nota(nombre, LocalDateTime.now(), texto, importante))
}

fun buscarPorNombre() {
    println("\n*** Buscar nota ***")
    println("Introduzca el nombre de la nota")
    val nombre = readlnOrNull().toString()
    if (nombre.isEmpty()){
        println("El nombre no puede ser vacio")
        return
    }
    println("Notas encontradas: ")
    notas.filter { it.nombre.contains(nombre) }.forEach {
        println(it)
    }
}

fun marcarImportante() {
    println("\n*** Marcar nota como importante ***")
    mostrarNotas()
    print("Escribe el nombre de la nota que quieras marcar como importante: ")
    val nota = readlnOrNull()?.trim().toString().lowercase(getDefault())

    notas.filter { it.nombre.contains(nota) }.forEach {
        it.importante = true
    }

}

fun mostrarNotas() {
    println("\n*** Mostrar notas ***")
    println("*** Notas cargadas encontradas ***")
    notas.forEach { nota -> println(nota.nombre) }
}

fun exportarNota() {
    println("\n*** Exportar nota ***")
}