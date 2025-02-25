class Mago(
    nombre: String,
    vida: Int = 5,
    ataque: Int = 4,
    defensa: Int = 3,
    velocidad: Int = 6,
    mana: Int = 10,
    arma: Arma = Arma("Báculo de madera", bonusAtaque = 1, bonusMana = 1)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma)
