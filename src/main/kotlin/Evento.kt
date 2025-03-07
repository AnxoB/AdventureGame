data class Opcion(
    val descripcion: String,
    val accion: (Personaje) -> Unit
)

class Evento(
    val titulo: String,
    val descripcion: String,
    val opciones: List<Opcion>
) {
    fun mostrarEvento() {
        println("$titulo")
        pausa(1000)
        println("$descripcion")
        pausa(1000)
        opciones.forEachIndexed { index, opcion ->
            println("${index + 1}. ${opcion.descripcion}")
            pausa(1000)
        }
    }

    fun tomarDecision(personaje: Personaje) {
        var opcionValida = false
        while (!opcionValida) {
            println("Selecciona una opción:")
            val indice = readLine()?.toIntOrNull() ?: 0
            if (indice in 1..opciones.size) {
                opciones[indice - 1].accion(personaje)
                opcionValida = true
            } else {
                println("Opción no válida, por favor introduce un valor correcto")
                opciones.forEachIndexed { index, opcion ->
                    println("${index + 1}. ${opcion.descripcion}")
                }
            }
        }
    }
}