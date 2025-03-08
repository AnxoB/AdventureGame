import kotlin.random.Random

open class Personaje(
    val nombre: String,
    var vida: Int,
    var fuerza: Int,
    var destreza: Int,
    var defensa: Int,
    var velocidad: Int,
    var magia: Int,
    var arma: Arma,
    var monedas: Int = 5
) {
    var nivel: Int = 1
    var experiencia: Int = 0
    private val experienciaNecesaria: Int
        get() = nivel * 10

    init {
        vida += arma.bonusVida
        fuerza += arma.bonusFuerza
        destreza += arma.bonusDestreza
        defensa += arma.bonusDefensa
        velocidad += arma.bonusVelocidad
        magia += arma.bonusMagia
    }

    open fun mejorarEstadisticas() {
        // Método vacío en la clase base, se sobreescribe en cada clase derivada
    }

    fun mostrarEstadisticas() {
        println("Nombre: $nombre")
        println("Vida: $vida")
        println("Fuerza: $fuerza")
        println("Destreza: $destreza")
        println("Defensa: $defensa")
        println("Velocidad: $velocidad")
        println("Maná: $magia")
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
        fuerza += arma.bonusFuerza
        destreza += arma.bonusDestreza
        defensa += arma.bonusDefensa
        velocidad += arma.bonusVelocidad
        magia += arma.bonusMagia
    }

    private fun quitarBonusArma(arma: Arma) {
        vida -= arma.bonusVida
        fuerza -= arma.bonusFuerza
        destreza -= arma.bonusDestreza
        defensa -= arma.bonusDefensa
        velocidad -= arma.bonusVelocidad
        magia -= arma.bonusMagia
    }
}