package p01ejerciciosbasicos.obligatorios

data class Libro(
    var nombre: String,
    var autor: String,
    var añoPubli: Int,
    var disponible: Boolean
)

val biblioteca = mutableListOf<Libro>()

fun main(){
    biblioteca.add(Libro("Don Quijote", "Miguel de Cervantes", 1605, true))
    biblioteca.add(Libro("Los Juegos del Hambre", "Suzanne Collins", 2008, false))
    biblioteca.add(Libro("Cien años de soledad", "Gabriel García Márquez", 1967, true))

    var textoUsuario: Int? = null
    do {
        println("\n1. Buscar libro por autor")
        println("2. Buscar libros por rango de años")
        println("3. Obtener solo los libros disponibles")
        println("4. Calcular estadsticas de la biblioteca")
        println("5. Buscar libros por titulo")
        println("6. Mostrar libros ordenados por año de publicacion")
        println("7. Agregar un nuevo libro")
        println("8. Salir")
        print("Ingrese una opcion: ")

        try {
            textoUsuario = readLine()!!.toInt()
            when (textoUsuario) {
                1 -> libroAutor()
                2 -> libroRango()
                3 -> libroDispo()
                4 -> statsBiblio()
                5 -> busquedaTitulo()
                6 -> libAño()
                7 -> nuevoLibro()
                8 -> println("Saliendo....")
                else -> println("Ingrese un numero del 1 al 8.")
            }
        } catch (e: NumberFormatException) {
            println("\nEntrada no válida. Debes escribir un número del 1 al 8.")
        }
    } while (textoUsuario != 8)
}

fun libroAutor() {
    println("Introduce el autor del libro que quieras buscar: ")
    val autorBuscado = readLine()
    val encontrado = biblioteca.filter{ it.autor.equals(autorBuscado, ignoreCase = true) }

    if (encontrado.isEmpty()) {
        println("\nNo se encontraron libros de ese autor")
    } else{
        println("\nlibros encontrados de $autorBuscado: ")
        for(Libro in encontrado){
            val disponibleTexto = if (Libro.disponible) "si" else "no"
            println("\n${Libro.nombre} - ${Libro.autor} - Año de publicacion: ${Libro.añoPubli} - Disponible: ${disponibleTexto}" )
        }
    }
}
fun libroRango() {
    println("Introduce el año menor: ")
    val añoMin = readLine()!!.toInt()
    println("Introduce el año mayor: ")
    val añoMax = readLine()!!.toInt()

    val encontrado = biblioteca.filter { it.añoPubli in añoMin..añoMax}

    if (encontrado.isEmpty()) {
        println("\nNo se encontraron libros en ese rango de años")
    } else{
        println("\nlibros entre $añoMin y $añoMax: ")
        for(Libro in encontrado){
            val disponibleTexto = if (Libro.disponible) "si" else "no"
            println("\n${Libro.nombre} - ${Libro.autor} - Año de publicacion: ${Libro.añoPubli} - Disponible: ${disponibleTexto}" )
        }
    }
}
fun libroDispo() {
    for(Libro in biblioteca){
        if (Libro.disponible == true) {
            println("\n${Libro.nombre} - ${Libro.autor} - Año de publicacion: ${Libro.añoPubli}")
        }
    }

}
fun statsBiblio() {
    println("Libros totales de la biblioteca: ${biblioteca.size}")
    println("Cuantos libros le pertenecen a cada autor: ")
    val conteo = biblioteca.groupingBy { it.autor }.eachCount()
    for ((autor, cantidad) in conteo) {
        println("${autor} - ${cantidad}")
    }
}

fun busquedaTitulo() {
    println("Introduce el titulo del libro que quieras buscar: ")
    val tituloBuscado = readLine()
    val encontrado = biblioteca.filter{ it.nombre.equals(tituloBuscado, ignoreCase = true) }
    if (encontrado.isEmpty()) {
        println("\nNo se encontraron libros en ese titulo")
    } else {
        println("\nlibros encontradoson el titulo de $tituloBuscado: ")
        for(Libro in encontrado){
            val disponibleTexto = if (Libro.disponible) "si" else "no"
            println("\n${Libro.nombre} - ${Libro.autor} - Año de publicacion: ${Libro.añoPubli} - Disponible: ${disponibleTexto}" )
        }
    }
}

fun libAño(){
    println("Libros ordenados por año de publicación: ")
    val ordenado = biblioteca.sortedBy{ it.añoPubli }
    for (Libro in ordenado){
        val disponibleTexto = if (Libro.disponible) "si" else "no"
        println("${Libro.añoPubli} - ${Libro.nombre} - ${Libro.autor} - Disponible: $disponibleTexto")
    }
}

fun nuevoLibro() {
    print("Introduce titulo del libro: ")
    val nombre = readLine()
    print("Introduce autor del libro: ")
    val autor = readLine()
    print("Introduce año de publicacion del libro: ")
    val añoPubli = readLine()!!.toInt()

    var disponibleBoolean: Boolean? = null

    while (disponibleBoolean == null) {
        print("Introduce si esta disponible el libro ( si / no ): ")
        val disponibleInput = readLine()?.trim()

        if(disponibleInput.equals("si", ignoreCase = true)) {
            disponibleBoolean = true
        } else if (disponibleInput.equals("no", ignoreCase = true)) {
            disponibleBoolean = false
        } else{
            println("Entrada no válida, Debe escribir exactamente \"si\" o \"no\"")
        }
    }
    biblioteca.add(Libro("$nombre", "$autor", añoPubli, disponibleBoolean))
    println("Libro agregado con exito")
}