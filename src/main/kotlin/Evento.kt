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
        println("$descripcion")
        opciones.forEachIndexed { index, opcion ->
            println("${index + 1}. ${opcion.descripcion}")
        }
    }

    fun tomarDecision(indice: Int, personaje: Personaje) {
        if (indice in 1..opciones.size) {
            opciones[indice - 1].accion(personaje)
        } else {
            println("Opción no válida")
        }
    }
}