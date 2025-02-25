open class Personaje(
    val name: String,
    var health: Int,
    var attack: Int,
    var defense: Int,
    var speed: Int,
    var mana: Int
) {
    fun displayStats() {
        println("Name: $name")
        println("Health: $health")
        println("Attack: $attack")
        println("Defense: $defense")
        println("Speed: $speed")
        println("Mana: $mana")
    }
}