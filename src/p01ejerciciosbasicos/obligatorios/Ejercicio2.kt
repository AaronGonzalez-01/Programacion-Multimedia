package p01ejerciciosbasicos.obligatorios

class TemperaturaInvalidaException(message: String) : Exception(message)
class FormatoNumeroInvalidoException(message: String) : Exception(message)

data class Temperatura(
    val valor: Double,
    val escala: Escala
)

enum class Escala {
    CELSIUS, FAHRENHEIT, KELVIN
}

fun celsiusAFahrenheit(celsius: Double): Double =
    celsius * 9.0 / 5.0 + 32.0

fun fahrenheitACelsius(f: Double): Double =
    (f - 32.0) * 5.0 / 9.0

fun celsiusAKelvin(c: Double): Double =
    c + 273.15

fun kelvinACelsius(k: Double): Double =
    k - 273.15


fun validarTemperatura(temperatura: Temperatura) {
    val esFisica = when (temperatura.escala) {
        Escala.CELSIUS -> temperatura.valor >= -273.15
        Escala.KELVIN -> temperatura.valor >= 0.0
        Escala.FAHRENHEIT -> temperatura.valor >= -459.67
    }

    val esTeorica = when (temperatura.escala) {
        Escala.CELSIUS -> temperatura.valor <= 1e6
        Escala.KELVIN -> temperatura.valor <= 1e6 + 273.15
        Escala.FAHRENHEIT -> temperatura.valor <= celsiusAFahrenheit(1e6)
    }

    if (!esFisica) {
        throw TemperaturaInvalidaException(
            "La temperatura ${temperatura.valor} en ${temperatura.escala} no es físicamente posible (por debajo del cero absoluto)."
        )
    }

    if (!esTeorica) {
        throw TemperaturaInvalidaException(
            "La temperatura ${temperatura.valor} en ${temperatura.escala} no es teóricamente razonable para este conversor."
        )
    }
}

fun convertir(temperatura: Temperatura, destino: Escala): Result<Temperatura> {
    return try {
        validarTemperatura(temperatura)

        val resultado = when (temperatura.escala to destino) {
            // mismas escalas → nada que hacer
            temperatura.escala to temperatura.escala -> temperatura

            Escala.CELSIUS to Escala.FAHRENHEIT -> {
                Temperatura(celsiusAFahrenheit(temperatura.valor), Escala.FAHRENHEIT)
            }
            Escala.FAHRENHEIT to Escala.CELSIUS -> {
                Temperatura(fahrenheitACelsius(temperatura.valor), Escala.CELSIUS)
            }
            Escala.CELSIUS to Escala.KELVIN -> {
                Temperatura(celsiusAKelvin(temperatura.valor), Escala.KELVIN)
            }
            Escala.KELVIN to Escala.CELSIUS -> {
                Temperatura(kelvinACelsius(temperatura.valor), Escala.CELSIUS)
            }
            else -> throw UnsupportedOperationException(
                "Conversión de ${temperatura.escala} a $destino no implementada."
            )
        }

        Result.success(resultado)
    } catch (e: Exception) {
        Result.failure(e)
    }
}


fun leerEscala(mensaje: String): Escala? {
    println(mensaje)
    println("1. Celsius (C)")
    println("2. Fahrenheit (F)")
    println("3. Kelvin (K)")
    print("Opción: ")
    return when (readLine()?.trim()) {
        "1", "C", "c" -> Escala.CELSIUS
        "2", "F", "f" -> Escala.FAHRENHEIT
        "3", "K", "k" -> Escala.KELVIN
        else -> null
    }
}

fun menuInteractivo2() {
    while (true) {
        println("===== CONVERSOR DE TEMPERATURAS =====")
        println("1. Hacer conversión")
        println("2. Salir")
        print("Elige una opción: ")

        when (readLine()?.trim()) {
            "1" -> {
                // Bucle de conversiones múltiples
                while (true) {
                    val escalaOrigen = leerEscala("\nElige escala ORIGEN:")
                    if (escalaOrigen == null) {
                        println("Escala origen inválida.\n")
                        break
                    }

                    val escalaDestino = leerEscala("\nElige escala DESTINO:")
                    if (escalaDestino == null) {
                        println("Escala destino inválida.\n")
                        break
                    }

                    print("\nIntroduce la temperatura en $escalaOrigen: ")
                    val input = readLine()
                    val valor = input?.toDoubleOrNull()
                    if (valor == null) {
                        println("Error: formato numérico inválido.\n")
                        continue
                    }

                    val tempOrigen = Temperatura(valor, escalaOrigen)
                    val resultado = convertir(tempOrigen, escalaDestino)

                    resultado
                        .onSuccess { t ->
                            println("Resultado: ${t.valor} $escalaDestino\n")
                        }
                        .onFailure { e ->
                            when (e) {
                                is TemperaturaInvalidaException -> println("Error: ${e.message}\n")
                                is FormatoNumeroInvalidoException -> println("Error de formato: ${e.message}\n")
                                else -> println("Error inesperado: ${e.message}\n")
                            }
                        }

                    print("¿Quieres hacer otra conversión? (s/n): ")
                    val seguir = readLine()?.trim()?.lowercase()
                    if (seguir != "s") {
                        println()
                        break
                    }
                }
            }

            "2" -> {
                println("Saliendo del conversor...")
                return
            }

            else -> println("Opción inválida.\n")
        }
    }
}

fun main() {
    menuInteractivo2()
}
