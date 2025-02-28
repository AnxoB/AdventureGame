import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    println("Bienvenido al juego de aventuras!")
    println("Por favor, introduce tu nombre:")
    val playerName = scanner.nextLine()

    println("Hola, $playerName! Por favor, elige tu personaje:")
    println("1. Mago")
    println("2. Caballero")
    println("3. Arquero")
    println("4. Picaro")

    val choice = scanner.nextLine().toInt()
    val character = when (choice) {
        1 -> Mago(playerName)
        2 -> Caballero(playerName)
        3 -> Arquero(playerName)
        4 -> Picaro(playerName)
        else -> {
            println("Opción no válida, seleccionando Mago por defecto.")
            Mago(playerName)
        }
    }

    println("Has elegido a ${character::class.simpleName}. Estas son sus características iniciales:")
    character.mostrarEstadisticas()


    fun seleccionarEvento(nivel: Int): Evento? {
        val posiblesEventos = when (nivel) {
            in 0..10 -> eventosFaciles
            in 11..20 -> eventosMedios
            else -> eventosDificiles
        }
        return if (posiblesEventos.isNotEmpty()) {
            posiblesEventos.random().also { posiblesEventos.remove(it) }
        } else null
    }

    while (true) {
        val evento = seleccionarEvento(character.nivel)
        if (evento != null) {
            evento.mostrarEvento()
            println("Elige una opción:")
            val decision = readLine()?.toIntOrNull() ?: 0
            evento.tomarDecision(decision, character)
            character.mostrarEstadisticas()
        } else {
            println("No hay más eventos disponibles para este nivel.")
            break
        }
    }

    println("Presiona Enter para salir...")
    readln()

}