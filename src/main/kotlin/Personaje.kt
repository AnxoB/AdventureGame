open class Personaje(
    val nombre: String,
    var vida: Int,
    var ataque: Int,
    var defensa: Int,
    var velocidad: Int,
    var mana: Int,
    val arma: Arma
) {
    init {
        vida += arma.bonusVida
        ataque += arma.bonusAtaque
        defensa += arma.bonusDefensa
        velocidad += arma.bonusVelocidad
        mana += arma.bonusMana
    }

    fun mostrarEstadisticas() {
        println("Nombre: $nombre")
        println("Vida: $vida")
        println("Ataque: $ataque")
        println("Defensa: $defensa")
        println("Velocidad: $velocidad")
        println("Maná: $mana")
        println("Arma: ${arma.nombre}")
    }
}