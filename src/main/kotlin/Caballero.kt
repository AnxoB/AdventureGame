class Caballero(
    nombre: String,
    vida: Int = 9,
    ataque: Int = 6,
    defensa: Int = 8,
    velocidad: Int = 4,
    mana: Int = 3,
    arma: Arma = Arma("Espada de madera", bonusAtaque = 1, bonusVida = 1)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma)