
class Picaro(
    nombre: String,
    vida: Int = 6,
    ataque: Int = 9,
    defensa: Int = 3,
    velocidad: Int = 10,
    mana: Int = 2,
    arma: Arma = Arma("Daga de madera", bonusVelocidad = 2)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma) {

    override fun mejorarEstadisticas() {
        val stats = listOf("vida", "ataque", "defensa", "velocidad", "mana")
        val probabilidades = listOf(0.05, 0.4, 0.05, 0.4, 0.1)
        val stat = stats.randomWeighted(probabilidades)
        when (stat) {
            "vida" -> vida += 2
            "ataque" -> ataque += 3
            "defensa" -> defensa += 1
            "velocidad" -> velocidad += 3
            "mana" -> mana += 1
        }
    }
}
