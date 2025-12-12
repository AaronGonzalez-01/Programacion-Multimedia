package p01ejerciciosbasicos.obligatorios

data class EstadisticasTexto(
    val caracteres: Int,
    val palabras: Int,
    val lineas: Int,
    val palabraMasFrecuente: String?,
    val frecuenciaPalabras: Map<String, Int>,
    val longitudMediaPalabras: Double
)


fun normalizarTexto(texto: String): String =
    texto
        .lowercase()
        .replace(Regex("[^a-záéíóúüñ0-9\\s]"), " ").replace(Regex("\\s+"), " ").trim()


fun contarCaracteres(texto: String): Int =
    texto.length


fun contarPalabras(textoNormalizado: String): Int =
    textoNormalizado
        .split(" ")
        .filter { it.isNotBlank() }
        .size

fun contarLineas(texto: String): Int =
    if (texto.isEmpty()) 0 else texto.split("\n").size


fun encontrarPalabraMasFrecuente(textoNormalizado: String): String? {
    val palabras = textoNormalizado
        .split(" ")
        .filter { it.isNotBlank() }

    if (palabras.isEmpty()) return null

    val frecuencias = palabras.groupBy { it }.mapValues { (_, lista) -> lista.size }
    return frecuencias.maxByOrNull { it.value }?.key
}


fun frecuenciaPalabras(textoNormalizado: String): Map<String, Int> =
    textoNormalizado
        .split(" ")
        .filter { it.isNotBlank() }
        .groupBy { it }
        .mapValues { (_, lista) -> lista.size }


fun longitudPromedioPalabras(textoNormalizado: String): Double {
    val palabras = textoNormalizado
        .split(" ")
        .filter { it.isNotBlank() }

    if (palabras.isEmpty()) return 0.0

    return palabras.map { it.length }.average()
}


fun analizarTexto(texto: String): EstadisticasTexto {
    val normalizado = normalizarTexto(texto)

    val caracteres = contarCaracteres(texto)
    val palabras = contarPalabras(normalizado)
    val lineas = contarLineas(texto)
    val palabraFrecuente = encontrarPalabraMasFrecuente(normalizado)
    val frecuencias = frecuenciaPalabras(normalizado)
    val longitudMedia = longitudPromedioPalabras(normalizado)

    return EstadisticasTexto(
        caracteres = caracteres,
        palabras = palabras,
        lineas = lineas,
        palabraMasFrecuente = palabraFrecuente,
        frecuenciaPalabras = frecuencias,
        longitudMediaPalabras = longitudMedia
    )
}


fun main() {
    val texto = """
        Hola, hola. Esto es un texto de prueba.
        Este texto sirve para probar el analizador de textos.
        Hola otra vez.
    """.trimIndent()

    val stats = analizarTexto(texto)
    println(stats)
}