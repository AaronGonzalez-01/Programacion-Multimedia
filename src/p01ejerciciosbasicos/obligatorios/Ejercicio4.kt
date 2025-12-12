package p01ejerciciosbasicos.obligatorios

data class Contacto(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String,
    var favorito: Boolean = false
)

class ContactoInvalidoException(message: String) : Exception(message)
class EmailInvalidoException(message: String) : Exception(message)
class TelefonoInvalidoException(message: String) : Exception(message)
class NombreInvalidoException(message: String) : Exception(message)

fun validarEmail(email: String) {
    val pattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    if (!pattern.matches(email.trim())) {
        throw EmailInvalidoException("Email inválido: $email")
    }
}

fun validarTelefono(telefono: String) {
    val limpio = telefono.replace(" ", "").trim()
    val regex = Regex("^\\d{9,15}$")
    if (!regex.matches(limpio)) {
        throw TelefonoInvalidoException("Teléfono inválido: $telefono")
    }
}

fun validarNombre(nombre: String) {
    val limpio = nombre.trim()
    if (limpio.length < 2) {
        throw NombreInvalidoException("Nombre inválido: '$nombre'")
    }
}

fun crearContacto(
    id: Int,
    nombre: String,
    email: String,
    telefono: String,
    favorito: Boolean = false
): Result<Contacto> {
    return try {
        validarNombre(nombre)
        validarEmail(email)
        validarTelefono(telefono)
        Result.success(Contacto(id, nombre.trim(), email.trim(), telefono.trim(), favorito))
    } catch (e: Exception) {
        Result.failure(e)
    }
}


fun buscarPorNombre(contactos: List<Contacto>, termino: String): List<Contacto> {
    val t = termino.trim().lowercase()
    return contactos.filter { it.nombre.lowercase().contains(t) }
}

fun obtenerFavoritos(contactos: List<Contacto>): List<Contacto> =
    contactos.filter { it.favorito }

fun obtenerOrdenados(contactos: List<Contacto>): List<Contacto> =
    contactos.sortedBy { it.nombre.lowercase() }


fun toggleFavorito(contactos: MutableList<Contacto>, id: Int): Boolean {
    val contacto = contactos.find { it.id == id } ?: return false
    contacto.favorito = !contacto.favorito
    return true
}


fun eliminarContacto(contactos: MutableList<Contacto>, id: Int): Boolean =
    contactos.removeIf { it.id == id }


fun mostrarContacto(contacto: Contacto) {
    println("ID: ${contacto.id}")
    println("Nombre: ${contacto.nombre}")
    println("Email: ${contacto.email}")
    println("Teléfono: ${contacto.telefono}")
    println("Favorito: ${if (contacto.favorito) "Sí" else "No"}")
    println("------------")
}


fun menuInteractivo() {
    val contactos = mutableListOf<Contacto>()
    var siguienteId = 1

    while (true) {
        println("===== GESTOR DE CONTACTOS =====")
        println("1. Crear contacto")
        println("2. Buscar por nombre")
        println("3. Ver favoritos")
        println("4. Ver todos ordenados")
        println("5. Alternar favorito")
        println("6. Eliminar contacto")
        println("7. Salir")
        print("Elige una opción: ")

        when (readLine()?.trim()) {
            "1" -> {
                try {
                    print("Nombre: ")
                    val nombre = readLine().orEmpty()

                    print("Email: ")
                    val email = readLine().orEmpty()

                    print("Teléfono: ")
                    val telefono = readLine().orEmpty()

                    val resultado = crearContacto(siguienteId, nombre, email, telefono)

                    resultado
                        .onSuccess { c ->
                            contactos.add(c)
                            siguienteId++
                            println("Contacto creado correctamente.\n")
                        }
                        .onFailure { e ->
                            println("Error al crear contacto: ${e.message}\n")
                        }

                } catch (e: Exception) {
                    println("Error inesperado: ${e.message}\n")
                }
            }

            "2" -> {
                print("Introduce nombre o parte del nombre: ")
                val termino = readLine().orEmpty()
                val resultados = buscarPorNombre(contactos, termino)
                if (resultados.isEmpty()) {
                    println("No se encontraron contactos.\n")
                } else {
                    resultados.forEach { mostrarContacto(it) }
                }
            }

            "3" -> {
                val favoritos = obtenerFavoritos(contactos)
                if (favoritos.isEmpty()) {
                    println("No hay contactos favoritos.\n")
                } else {
                    favoritos.forEach { mostrarContacto(it) }
                }
            }

            "4" -> {
                val ordenados = obtenerOrdenados(contactos)
                if (ordenados.isEmpty()) {
                    println("No hay contactos.\n")
                } else {
                    ordenados.forEach { mostrarContacto(it) }
                }
            }

            "5" -> {
                print("Introduce ID del contacto para alternar favorito: ")
                val input = readLine()
                val id = input?.toIntOrNull()
                if (id == null) {
                    println("ID inválido.\n")
                    continue
                }
                val actualizado = toggleFavorito(contactos, id)
                if (actualizado) {
                    println("Favorito actualizado.\n")
                } else {
                    println("No se encontró contacto con ese ID.\n")
                }
            }

            "6" -> {
                print("Introduce ID del contacto a eliminar: ")
                val input = readLine()
                val id = input?.toIntOrNull()
                if (id == null) {
                    println("ID inválido.\n")
                    continue
                }
                val eliminado = eliminarContacto(contactos, id)
                if (eliminado) {
                    println("Contacto eliminado.\n")
                } else {
                    println("No se encontró contacto con ese ID.\n")
                }
            }

            "7" -> {
                println("Saliendo del gestor...")
                return
            }

            else -> println("Opción inválida.\n")
        }
    }
}

fun main() {
    menuInteractivo()
}