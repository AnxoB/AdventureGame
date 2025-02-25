import kotlin.random.Random

val eventoMercaderSospechoso = Evento(
    titulo = "El Mercader Sospechoso",
    descripcion = "Un mercader de aspecto extraño te ofrece una poción por 5 monedas.",
    opciones = listOf(
        Opcion("Comprarla") { personaje ->
            if (personaje.monedas >= 5) {
                personaje.monedas -= 5
                if (Random.nextBoolean()) {
                    println("Has recibido una Poción de Vida (+10 HP)")
                    personaje.vida += 10
                } else {
                    println("Has recibido una Poción Envenenada (-5 HP)")
                    personaje.vida -= 5
                }
            } else {
                println("No tienes suficientes monedas")
            }
        },
        Opcion("No comprarla") {
            println("Sigues tu camino sin comprar nada.")
        },
        Opcion("Amenazarlo") { personaje ->
            if (Random.nextBoolean()) {
                println("El mercader te da la poción gratis.")
                if (Random.nextBoolean()) {
                    println("Has recibido una Poción de Vida (+10 HP)")
                    personaje.vida += 10
                } else {
                    println("Has recibido una Poción Envenenada (-5 HP)")
                    personaje.vida -= 5
                }
            } else {
                println("Un guardia sorpresa te ataca (Combate).")
                val guardia = Enemigo("Guardia", vida = 20, ataque = 5, defensa = 3, velocidad = 4)
                val batalla = Batalla(personaje, guardia)
                batalla.iniciar()
            }
        }
    )
)

val eventoCofreOxidado = Evento(
    titulo = "El Cofre Oxidado",
    descripcion = "Encuentras un viejo cofre en el bosque cubierto de musgo y óxido. Parece frágil, pero podría contener algo útil.",
    opciones = listOf(
        Opcion("Abrirlo con cuidado") { personaje ->
            if (personaje.velocidad >= 6){
                when (personaje) {
                    is Caballero -> {
                        println("Has encontrado una Espada de Madera Mejorada (+2 Ataque +2 Defensa).")
                        personaje.cambiarArma(Arma("Espada de Madera Mejorada", bonusAtaque = 2, bonusDefensa = 2))
                    }
                    is Arquero -> {
                        println("Has encontrado un Arco Tallado (+3 Ataque +1 Velocidad).")
                        personaje.cambiarArma(Arma("Arco Tallado", bonusAtaque = 3, bonusVelocidad = 1))
                    }
                    is Mago -> {
                        println("Has encontrado un Bastón de Roble (+2 Ataque, +2 Mana).")
                        personaje.cambiarArma(Arma("Bastón de Roble", bonusAtaque = 2, bonusMana = 2))
                    }
                    is Picaro -> {
                        println("Has encontrado unas Dagas de Madera (+1 Ataque +3 Velocidad).")
                        personaje.cambiarArma(Arma("Dagas de Madera", bonusAtaque = 1, bonusVelocidad = 3))
                    }
                    else -> println("No encuentras nada útil para tu clase.")
                }
            } else {
                println("No has encontrado nada útil en el cofre")
            }

        },
        Opcion("Forzarlo") { personaje ->
            println("¡Se activa una trampa! Pierdes 3 puntos de vida.")
            personaje.vida -= 3
        },
        Opcion("Ignorarlo") {
            println("Decides ignorar el cofre y seguir tu camino.")
        }
    )
)

val eventosFaciles = mutableListOf(eventoMercaderSospechoso, eventoCofreOxidado)
val eventosMedios = mutableListOf<Evento>()
val eventosDificiles = mutableListOf<Evento>()