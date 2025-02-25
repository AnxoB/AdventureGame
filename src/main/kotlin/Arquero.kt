import kotlin.random.Random

class Arquero(
    nombre: String,
    vida: Int = 6,
    ataque: Int = 8,
    defensa: Int = 4,
    velocidad: Int = 9,
    mana: Int = 3,
    arma: Arma = Arma("Arco de madera", bonusAtaque = 2)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma) {

    override fun mejorarEstadisticas() {
        val stats = listOf("vida", "ataque", "defensa", "velocidad", "mana")
        val probabilidades = listOf(0.4, 0.4, 0.05, 0.1, 0.05)
        val stat = stats.randomWeighted(probabilidades)
        when (stat) {
            "vida" -> vida += 2
            "ataque" -> ataque += 3
            "defensa" -> defensa += 1
            "velocidad" -> velocidad += 3
            "mana" -> mana += 2
        }
    }
}
