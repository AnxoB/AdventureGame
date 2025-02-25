import kotlin.random.Random

class Enemigo(
    val nombre: String,
    var vida: Int,
    var ataque: Int,
    var defensa: Int,
    var velocidad: Int
) {
    fun mostrarEstadisticas() {
        println("Nombre: $nombre")
        println("Vida: $vida")
        println("Ataque: $ataque")
        println("Defensa: $defensa")
        println("Velocidad: $velocidad")
    }
}