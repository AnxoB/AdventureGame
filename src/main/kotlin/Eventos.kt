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
                    personaje.vida += 2
                } else {
                    println("Has recibido una Poción Envenenada (-5 HP)")
                    personaje.vida -= 2
                }
            } else {
                println("No tienes suficientes monedas")
            }
        },
        Opcion("No comprarla") {
            println("Sigues tu camino sin comprar nada.")
        },
        Opcion("Amenazarlo") { personaje ->
            if (personaje.ataque>10) {
                println("El mercader se siente amenazado y te da la poción gratis.")
                if (Random.nextBoolean()) {
                    println("Has recibido una Poción de Vida (+10 HP)")
                    personaje.vida += 2
                } else {
                    println("Has recibido una Poción Envenenada (-5 HP)")
                    personaje.vida -= 2
                }
            } else {
                println("El mercader no se siente amenazado y te ataca")
                val mercader = Enemigo("Mercader", vida = 20, ataque = 5, defensa = 3, velocidad = 4)
                val batalla = Batalla(personaje, mercader)
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
val posadaBrisavento = Evento (
    titulo = "La Posada de Brisavento",
    descripcion = "Caminando llegas a Brisavento, en la región de Los Dominios del Céfiro, un territorio de extensas llanuras, colinas ondulantes y cañones esculpidos por el viento. Se dice que los vientos que cruzan estas tierras traen consigo susurros del pasado y que algunos viajeros han escuchado voces en la brisa."+
    "Brisavento es conocida por su clima fresco, su mercado ambulante y su posada, El Último Hogar, donde viajeros y mercaderes se reúnen antes de continuar sus viajes.",
    opciones = listOf(
        Opcion("Hablar con el posadero") {
            println("Tenemos habitaciones disponibles a 5 monedas")
            Opcion("Aceptas y pagas las 5 monedas"){ personaje ->
                println("Descansas y tu vida aumenta en 5 puntos")
                personaje.monedas -= 5
                personaje.vida += 5
            }
            Opcion("Negociar"){ personaje ->
                if (personaje.mana > 10){
                    println("Consigues dormir gratis")
                    personaje.vida += 5
                } else{
                    println("Fallas en la negociación y aceptas dormir por 5 monedas")
                    personaje.monedas -= 5
                    personaje.vida += 5
                }
            }
            Opcion("Rechazar y volver al salon"){ personaje ->
                println("Descansas y tu vida aumenta en 5 puntos")
                personaje.vida += 5
            }
        },
        Opcion("Ir donde el mercader misterioso"){
            println("Un hombre con capa oscura vende objetos inusuales")
            Opcion("Robar al mercader"){ personaje ->
                if (personaje is Picaro || personaje.velocidad>10){
                    println("Robas una poción de vida y 5 monedas")
                    personaje.monedas += 5
                    //Falta pocion vida
                } else {
                    println("Fallas el robo y el mercader te desafía a un duelo")
                    val mercader = Enemigo("Mercader Misterioso", vida = 25, ataque = 8, defensa = 3, velocidad = 6)
                    val batalla = Batalla(personaje, mercader)
                    batalla.iniciar()
                    println("Hola")
                }
            }
        }


    )


)

val eventosFaciles = mutableListOf(eventoMercaderSospechoso, eventoCofreOxidado, posadaBrisavento)
val eventosMedios = mutableListOf<Evento>()
val eventosDificiles = mutableListOf<Evento>()