class Arquero(
    name: String,
    health: Int = 6,
    attack: Int = 8,
    defense: Int = 4,
    speed: Int = 9,
    mana: Int = 3
) : Personaje(name, health, attack, defense, speed, mana)