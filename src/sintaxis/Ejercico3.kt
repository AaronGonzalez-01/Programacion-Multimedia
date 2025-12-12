package sintaxis

class Ejercico3 {

}

data class Tarea(val id: Int, val descriptor: String, var completado: Boolean = false)

fun main(){
    //Inicailización una sintaxis.lista del objeto sintaxis.Tarea
    val tareas = mutableListOf<Tarea>()
    //Variable que nos permite navegar de tarea en tarea
    var siguienteId = 1;

    //Bucle while con funcionalidad de asistente
    while (true){
        println("\n*** Gestor de tareas ***")
        println("1. Agregar tarea")
        println("2. Listar tarea")
        println("3. Marcar como completada")
        println("4. Eliminar tarea")
        println("5. Salir")
        println("Opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Descripción: ")
                val descriptor =  readLine() ?: continue
                tareas.add(Tarea(siguienteId++, descriptor))
            }
            2 -> {
                if (tareas.isEmpty()){
                    println("No hay tareas")
                }else{
                    tareas.forEach { (id, descriptor, completado) ->
                        val estado = if(completado) "[✓]" else "[ ]"
                        println("$estado - $id - $descriptor")
                    }
                }
            }
            3 -> {
                println("ID de tarea: ")
                val id = readLine()?.toIntOrNull() ?: continue
                tareas.find { it.id == id }?.completado = true
                println("La tarea ha sido completada")
            }
            4 -> {
                println("ID de tarea: ")
                val id = readLine()?.toIntOrNull() ?: continue
                tareas.removeIf { it.id == id }
                println("La tarea ha sido eliminada")
            }
            5 -> break
        }
    }
}