
class Picaro(
    nombre: String,
    vida: Int = 6,
    fuerza: Int = 6,
    destreza: Int = 10,
    defensa: Int = 3,
    velocidad: Int = 10,
    magia: Int = 2,
    arma: Arma = Arma("Daga de madera", bonusVelocidad = 2)
) : Personaje(nombre, vida, fuerza, destreza, defensa, velocidad, magia, arma) {

    override fun mejorarEstadisticas() {
        val stats = listOf("vida", "fuerza", "destreza", "defensa", "velocidad", "magia")
        val probabilidades = listOf(0.1, 0.2, 0.3, 0.1, 0.25, 0.05)

        val incrementos = mutableMapOf("vida" to 0, "fuerza" to 0, "destreza" to 0, "defensa" to 0, "velocidad" to 0, "magia" to 0)

        // Función auxiliar para seleccionar una estadística basada en las probabilidades
        fun seleccionarEstadisticaProbabilistica(): String {
            val randomValue = Math.random()
            var cumulativeProbability = 0.0
            for ((index, probability) in probabilidades.withIndex()) {
                cumulativeProbability += probability
                if (randomValue <= cumulativeProbability) {
                    return stats[index]
                }
            }
            return stats.last() // Retorna la última estadística si no se selecciona ninguna
        }

        // Distribuir 5 puntos en las estadísticas seleccionadas
        repeat(5) {
            val stat = seleccionarEstadisticaProbabilistica()
            incrementos[stat] = incrementos[stat]!! + 1
            when (stat) {
                "vida" -> vida += 1
                "fuerza" -> fuerza += 1
                "destreza" -> destreza += 1
                "defensa" -> defensa += 1
                "velocidad" -> velocidad += 1
                "magia" -> magia += 1
            }
        }

        // Mostrar los incrementos al usuario
        println("Incrementos en tus estadísticas:")
        incrementos.forEach { (stat, incremento) ->
            if (incremento > 0) {
                val valorActual = when (stat) {
                    "vida" -> vida
                    "fuerza" -> fuerza
                    "destreza" -> destreza
                    "defensa" -> defensa
                    "velocidad" -> velocidad
                    "magia" -> magia
                    else -> 0
                }
                println("$stat: $valorActual (+$incremento)")
            }
        }
    }
}
