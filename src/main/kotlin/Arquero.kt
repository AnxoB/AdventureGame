class Arquero(
    nombre: String,
    vida: Int = 6,
    ataque: Int = 8,
    defensa: Int = 4,
    velocidad: Int = 9,
    mana: Int = 3,
    arma: Arma = Arma("Arco de madera", bonusAtaque = 2)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma)
