class Mago(
    nombre: String,
    vida: Int = 5,
    ataque: Int = 4,
    defensa: Int = 3,
    velocidad: Int = 6,
    mana: Int = 10,
    arma: Arma = Arma("Báculo de madera", bonusAtaque = 1, bonusMana = 1)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma) {

    override fun mejorarEstadisticas() {
        val stats = listOf("vida", "ataque", "defensa", "velocidad", "mana")
        val probabilidades = listOf(0.05, 0.45, 0.05, 0.05, 0.4)
        val stat = stats.randomWeighted(probabilidades)
        when (stat) {
            "vida" -> vida += 2
            "ataque" -> ataque += 2
            "defensa" -> defensa += 1
            "velocidad" -> velocidad += 2
            "mana" -> mana += 3
        }
    }
}