import kotlin.random.Random

open class Personaje(
    val nombre: String,
    var vida: Int,
    var ataque: Int,
    var defensa: Int,
    var velocidad: Int,
    var mana: Int,
    var arma: Arma,
    var monedas: Int = 5
) {
    var nivel: Int = 1
    var experiencia: Int = 0
    private val experienciaNecesaria: Int
        get() = nivel * 10

    init {
        vida += arma.bonusVida
        ataque += arma.bonusAtaque
        defensa += arma.bonusDefensa
        velocidad += arma.bonusVelocidad
        mana += arma.bonusMana
    }

    open fun mejorarEstadisticas() {
        // Método vacío en la clase base, se sobreescribe en cada clase derivada
    }

    fun mostrarEstadisticas() {
        println("Nombre: $nombre")
        println("Vida: $vida")
        println("Ataque: $ataque")
        println("Defensa: $defensa")
        println("Velocidad: $velocidad")
        println("Maná: $mana")
        println("Arma: ${arma.nombre}")
        println("Nivel: $nivel")
        println("Experiencia: $experiencia / $experienciaNecesaria")
        println("Monedas: $monedas")
    }

    fun ganarExperiencia(cantidad: Int) {
        experiencia += cantidad
        while (experiencia >= experienciaNecesaria) {
            experiencia -= experienciaNecesaria
            subirNivel()
        }
    }

    private fun subirNivel() {
        nivel++
        println("$nombre ha subido al nivel $nivel!")
        mejorarEstadisticas()
    }

    fun cambiarArma(nuevaArma: Arma) {
        quitarBonusArma(arma)
        arma = nuevaArma
        aplicarBonusArma(nuevaArma)
    }

    private fun aplicarBonusArma(arma: Arma) {
        vida += arma.bonusVida
        ataque += arma.bonusAtaque
        defensa += arma.bonusDefensa
        velocidad += arma.bonusVelocidad
        mana += arma.bonusMana
    }

    private fun quitarBonusArma(arma: Arma) {
        vida -= arma.bonusVida
        ataque -= arma.bonusAtaque
        defensa -= arma.bonusDefensa
        velocidad -= arma.bonusVelocidad
        mana -= arma.bonusMana
    }
}