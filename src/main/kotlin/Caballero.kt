import kotlin.random.Random

class Caballero(
    nombre: String,
    vida: Int = 9,
    ataque: Int = 6,
    defensa: Int = 8,
    velocidad: Int = 4,
    mana: Int = 3,
    arma: Arma = Arma("Espada de madera", bonusAtaque = 1, bonusVida = 1)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma) {

    override fun mejorarEstadisticas() {
        val stats = listOf("vida", "ataque", "defensa", "velocidad", "mana")
        val probabilidades = listOf(0.1, 0.4, 0.4, 0.05, 0.05)
        val stat = stats.randomWeighted(probabilidades)
        when (stat) {
            "vida" -> vida += 3
            "ataque" -> ataque += 3
            "defensa" -> defensa += 2
            "velocidad" -> velocidad += 2
            "mana" -> mana += 1
        }
    }
}