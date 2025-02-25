class Picaro(
    nombre: String,
    vida: Int = 6,
    ataque: Int = 9,
    defensa: Int = 3,
    velocidad: Int = 10,
    mana: Int = 2,
    arma: Arma = Arma("Daga de madera", bonusVelocidad = 2)
) : Personaje(nombre, vida, ataque, defensa, velocidad, mana, arma)
