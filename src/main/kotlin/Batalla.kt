import kotlin.random.Random

class Batalla(
    private val personaje: Personaje,
    private val enemigo: Enemigo
) {
    fun iniciar() {
        println("¡Comienza la batalla entre ${personaje.nombre} y ${enemigo.nombre}!")

        while (personaje.vida > 0 && enemigo.vida > 0) {
            if (personaje.velocidad > enemigo.velocidad) {
                turno(personaje, enemigo)
                if (enemigo.vida > 0) {
                    turno(enemigo, personaje)
                }
            } else if (enemigo.velocidad > personaje.velocidad) {
                turno(enemigo, personaje)
                if (personaje.vida > 0) {
                    turno(personaje, enemigo)
                }
            } else {
                // Si tienen la misma velocidad, el turno es aleatorio
                if (Random.nextBoolean()) {
                    turno(personaje, enemigo)
                    if (enemigo.vida > 0) {
                        turno(enemigo, personaje)
                    }
                } else {
                    turno(enemigo, personaje)
                    if (personaje.vida > 0) {
                        turno(personaje, enemigo)
                    }
                }
            }
        }

        if (personaje.vida <= 0) {
            println("${personaje.nombre} ha sido derrotado por ${enemigo.nombre}.")
        } else {
            println("${enemigo.nombre} ha sido derrotado por ${personaje.nombre}.")
        }
    }

    private fun turno(atacante: Any, defensor: Any) {
        val (nombreAtacante, ataque, velocidadAtacante) = when (atacante) {
            is Personaje -> Triple(atacante.nombre, atacante.ataque, atacante.velocidad)
            is Enemigo -> Triple(atacante.nombre, atacante.ataque, atacante.velocidad)
            else -> throw IllegalArgumentException("Atacante desconocido")
        }

        val (nombreDefensor, defensa) = when (defensor) {
            is Personaje -> Pair(defensor.nombre, defensor.defensa)
            is Enemigo -> Pair(defensor.nombre, defensor.defensa)
            else -> throw IllegalArgumentException("Defensor desconocido")
        }

        val daño = maxOf(0, ataque - defensa)
        val velocidadDefensor = if (defensor is Personaje) defensor.velocidad else (defensor as Enemigo).velocidad
        val dañoReal = if (velocidadAtacante >= 2 * velocidadDefensor) daño * 2 else daño

        when (defensor) {
            is Personaje -> defensor.vida -= dañoReal
            is Enemigo -> defensor.vida -= dañoReal
        }

        println("$nombreAtacante [Vida: ${if (atacante is Personaje) atacante.vida else (atacante as Enemigo).vida}] hace $dañoReal de daño a $nombreDefensor [Vida: ${if (defensor is Personaje) defensor.vida else (defensor as Enemigo).vida}]")
        pausa(1000)
    }
}