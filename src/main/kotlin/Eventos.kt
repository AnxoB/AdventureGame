import kotlinx.coroutines.processNextEventInCurrentThread
import org.jetbrains.skia.Region
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
    descripcion = "Caminando llegas a Brisavento, en la región de Los Dominios del Céfiro, un territorio de extensas llanuras, colinas ondulantes y cañones esculpidos por el viento. Se dice que los vientos que cruzan estas tierras traen consigo susurros del pasado y que algunos viajeros han escuchado voces en la brisa.\n "+
    "Brisavento es conocida por su clima fresco, su mercado ambulante y su posada, El Último Hogar, donde viajeros y mercaderes se reúnen antes de continuar sus viajes.",
    opciones = listOf(
        Opcion("Hablar con el posadero") { personaje ->
            println("Tenemos habitaciones disponibles a 5 monedas")
            fun opcionesPosadero(personaje: Personaje) {
                val subOpciones = listOf(
                    Opcion("Aceptar y pagar las 5 monedas") {
                        println("Descansas y tu vida aumenta en 5 puntos")
                        personaje.monedas -= 5
                        personaje.vida += 5
                    },
                    Opcion("Negociar") {
                        if (personaje.mana > 10) {
                            println("Consigues dormir gratis")
                            personaje.vida += 5
                        } else {
                            println("Fallas en la negociación y aceptas dormir por 5 monedas")
                            personaje.monedas -= 5
                            personaje.vida += 5
                        }
                    },
                    Opcion("Rechazar e irte de la posada") {
                        println("Decides no alquilar una habitación y abandonas la posada.")
                    }
                )
                subOpciones.forEachIndexed { index, opcion ->
                    println("${index + 1}. ${opcion.descripcion}")
                }
                println("Selecciona una opción:")
                val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                subOpciones[opcionSeleccionada - 1].accion(personaje)
            }
            opcionesPosadero(personaje)
        },
        Opcion("Ir donde el mercader misterioso"){ personaje ->
            println("Un hombre con capa oscura vende objetos inusuales")
            fun opcionesMercaderMisterioso(personaje: Personaje) {
                val subOpciones = listOf(
                    Opcion("El mercader te ofrece un amuleto de la suerte"){
                        //Falta
                    },
                    Opcion("Robar al mercader") {
                        if (personaje is Picaro || personaje.velocidad > 10) {
                            println("Robas una poción de vida y 5 monedas")
                            personaje.monedas += 5
                            // Aquí falta la lógica para añadir la poción de vida
                        } else {
                            println("Fallas el robo y el mercader te desafía a un duelo")
                            val mercader = Enemigo("Mercader Misterioso", vida = 25, ataque = 8, defensa = 3, velocidad = 6)
                            val batalla = Batalla(personaje, mercader)
                            batalla.iniciar()
                            personaje.experiencia += 2
                            personaje.monedas += 2
                            println("Derrotas al mercader, consigues 2 de experiencia y 2 monedas")
                        }
                    },
                    Opcion("Ignorarlo y seguir explorando"){
                        println("Dejas al mercader con sus chanchullos y abandonas la posada")
                    }
                )
                subOpciones.forEachIndexed { index, opcion ->
                    println("${index + 1}. ${opcion.descripcion}")
                }
                println("Selecciona una opción:")
                val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                subOpciones[opcionSeleccionada - 1].accion(personaje)
            }
            opcionesMercaderMisterioso(personaje)
        },
        Opcion("Sentarte con unos aventureros"){ personaje ->
            println("Te sientas a charlar con un grupo variopinto de aventureros")
            fun opcionesCharla(personaje: Personaje){
                val subOpciones = listOf(
                    Opcion("Escuchar sus historias"){
                        println("Uno de los viajeros comenta el rumor del Santuario de los Susurros, construido por una orden de monjes que creían que el viento era un ser viviente, un guardián de secretos antiguos. Durante siglos, protegieron algo en su interior… algo lo bastante valioso como para que nunca revelaran su ubicación.\n"+
                        "Pero el tiempo se llevó a los monjes, y el santuario se perdió. Te cuenta que una vez estuvo allí, pero que no fue capaz de llegar al final de tesoro.\n"+
                        "-Sigue el camino al Cañón de las Mil Voces, donde los vientos silban como si estuvieran contando historias olvidadas. Cuando llegues, espera al anochecer. En las noches adecuadas, el viento cambia… no es un simple murmullo, sino una especie de canto.\n"+
                        "—Cuando veas tres caminos tallados en la roca, sabrás que estás cerca. Pero el verdadero desafío empieza después. El santuario no se abre a cualquiera… tienes que demostrar que entiendes el lenguaje del viento.")
                        println("Encandilado por las posibles recompensas que el santuario pueda tener en su interior, te despides de los viajeros y te lanzas en su busca")
                        println("Después de días de viaje, llegas a una zona donde el viento parece hablarte con palabras incomprensibles. Frente a ti, hay tres caminos, cada uno con señales talladas en la roca.")
                        fun opcionesSantuario(personaje: Personaje){
                            val subOpciones = listOf(
                                Opcion("Sendero Angosto entre acantilados (Velocidad > ?)"){
                                    println("El viento es fuerte y el suelo inestable")
                                    if (personaje.velocidad>15){
                                        println("Llegas al otro lado sin problemas")
                                        println("Al final del camino, descubres un antiguo altar de piedra con una inscripción:")
                                        fun recompensaSanturario(personaje: Personaje){
                                            println("El altar se abre y revela un arma que parece que porta el poder del viento")
                                            when (personaje) {
                                                is Caballero -> {
                                                    println("Has encontrado una Espada de Viento (+5 Ataque +5 Defensa) y ganas 5 de experiencia")
                                                    personaje.cambiarArma(Arma("Espada de Viento", bonusAtaque = 5, bonusDefensa = 5))
                                                    personaje.experiencia+=5
                                                }
                                                is Arquero -> {
                                                    println("Has encontrado un Arco de Viento (+5 Ataque +5 Velocidad) y ganas 5 de experiencia")
                                                    personaje.cambiarArma(Arma("Arco de Viento", bonusAtaque = 5, bonusVelocidad = 5))
                                                    personaje.experiencia+=5
                                                }
                                                is Mago -> {
                                                    println("Has encontrado un Bastón de Viento (+5 Ataque, +5 Mana) y ganas 5 de experiencia")
                                                    personaje.cambiarArma(Arma("Bastón de Viento", bonusAtaque = 5, bonusMana = 5))
                                                    personaje.experiencia+=5
                                                }
                                                is Picaro -> {
                                                    println("Has encontrado unas Dagas de Viento (+5 Ataque +5 Velocidad) y ganas 5 de experiencia")
                                                    personaje.cambiarArma(Arma("Dagas de Viento", bonusAtaque = 5, bonusVelocidad = 5))
                                                    personaje.experiencia+=5
                                                }
                                                else -> println("No encuentras nada útil para tu clase.")

                                            }
                                        }
                                        fun opcionesAltar(personaje: Personaje){
                                            val subOpciones = listOf(
                                                Opcion("Soplar sobre el altar (Mago o maná > ?)"){
                                                    if (personaje is Mago || personaje.mana > 15){
                                                        recompensaSanturario(personaje)
                                                    } else {
                                                        println("No ocurre nada. Te vas por donde has venido ya que no eres capaz de activar el altar")
                                                    }
                                                },
                                                Opcion("Leer la inscripción"){
                                                    println("No me ves, pero me sientes, viajo lejos sin detenerme.\nSilbo historias en las cumbres, y en tormentas suelo alzarte.\n¿Qué soy?")
                                                    println("Contagiado por una extraña sensación, te decides a pronunciar una palabra en alto:")
                                                    val solucion = readln()
                                                    if (solucion.lowercase()=="viento" || solucion.lowercase()=="el viento" || solucion.lowercase()=="elviento"){
                                                        recompensaSanturario(personaje)
                                                    } else{
                                                        println("Tan pronto pronuncias $solucion, una gran ráfaga de viento te empuja lejos del santuario")
                                                    }
                                                },
                                                Opcion("Forzar el altar (ataque > ?)"){
                                                    if (personaje.ataque > 20){
                                                        recompensaSanturario(personaje)
                                                    } else {
                                                        println("No consigues activar el altar y activas una trampa")
                                                        println("Pierdes 2 de vida")
                                                        personaje.vida -= 2
                                                    }
                                                },
                                            )
                                            subOpciones.forEachIndexed { index, opcion ->
                                                println("${index + 1}. ${opcion.descripcion}")
                                            }
                                            println("Selecciona una opción:")
                                            val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                                            subOpciones[opcionSeleccionada - 1].accion(personaje)
                                        }
                                        opcionesAltar(personaje)
                                    } else{
                                        println("Un fuerte golpe de viento te hace perder el equilibrio, aunque consigues avanzar")
                                        println("Pierdes 5 de vida")
                                        personaje.vida -= 5
                                    }
                                },
                                Opcion("Cueva oscura con extrañas corrientes de aire (Maná > ?)"){
                                    println("Dentro, el viento forma silbidos inquietantes. Hay inscripciones en las paredes.")
                                    if (personaje.mana>15){
                                        println("Eres capaz de leer los glifos y encuentras una sala secreta con un cofre, que contiene 20 monedas")
                                        println("Ganas 20 monedas y 5 de experiencia")
                                        personaje.monedas += 20
                                        personaje.experiencia += 5
                                    } else{
                                        println("No eres capaz de leer los glifos y avanzas a ciegas")
                                        if (Random.nextBoolean()) {
                                            println("Activas una trampa y huyes del lugar")
                                            println("Pierdes 5 de vida")
                                            personaje.vida += 5
                                        } else {
                                            println("No encuentras nada, así que te vas del lugar")
                                        }
                                    }
                                },
                                Opcion("Puente de piedra que cruza un abismo (Ataque > ?)"){
                                    println("El puente está agrietado, y el viento sopla mucha fuerza.")
                                    if (personaje.ataque>15){
                                        println("Llegas al otro lado sin problemas. Te encuentras a un pícaro que también estaba buscando el tesoro. Te ataca tan pronto entabla contacto visual")
                                        val bandido = Enemigo("Pícaro ladrón", vida = 15, ataque = 8, defensa = 3, velocidad = 10)
                                        val batalla = Batalla(personaje, bandido)
                                        batalla.iniciar()
                                        personaje.experiencia += 2
                                        personaje.monedas += 10
                                        println("Derrotas sin problema al pícaro, consigues 2 de experiencia y 10 monedas")
                                        println("Exploras la zona pero no encuentras nada. Al menos pudiste conseguir botín del pícaro")
                                    } else{
                                        println("Tan pronto das el primer paso, el puente se derrumba. No puedes continuar y abandonas el lugar")
                                        println("Pierdes 5 de vida")
                                        personaje.vida -= 5
                                    }
                                },
                                Opcion("Abandonar la zona"){
                                    println("No te sientes preparado para afrontar los desafíos, pero tienes la sensación de haber esquivado la muerte")
                                }
                            )
                            subOpciones.forEachIndexed { index, opcion ->
                                println("${index + 1}. ${opcion.descripcion}")
                            }
                            println("Selecciona una opción:")
                            val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                            subOpciones[opcionSeleccionada - 1].accion(personaje)
                        }
                        opcionesSantuario(personaje)
                    },
                    Opcion("Desafiar a uno a un pulso") {
                        println("Desafías a x, el mas fuerte a un pulso. Su aspecto es muy fuerte, pero te arriesgas a realizar una apuesta")
                        if (personaje.ataque>8){
                            println("Ganas el pulso sin apenas esfuerzo. X queda sorprendido y ganas la apuesta")
                            println("Ganas 3 monedas y 2 de experiencia")
                            personaje.monedas += 3
                            personaje.experiencia += 2
                        }
                    },
                    Opcion("Pasa la noche y bebe junto a ellos (Paga 3 monedas)"){
                        println("Pasas la noche con los aventureros, bebes, cantas, bailas... Y al día siguiente no recuerdas ni que has hecho")
                        println("Ganas 2 de vida, pierdes 3 monedas y ganas 2 de experiencia")
                        personaje.monedas -= 3
                        personaje.vida += 2
                        personaje.experiencia += 2
                    }
                )
                subOpciones.forEachIndexed { index, opcion ->
                    println("${index + 1}. ${opcion.descripcion}")
                }
                println("Selecciona una opción:")
                val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                subOpciones[opcionSeleccionada - 1].accion(personaje)
            }
            opcionesCharla(personaje)
        },
        Opcion("Subir a las habitaciones a colarte en la posada"){ personaje ->
            println("Subes al pasillo de las habitaciones y te encuentras con 2 puertas")
            fun opcionesPuerta(personaje: Personaje){
                val subOpciones = listOf(
                    Opcion("Colarte en la primera puerta (Clase pícaro o velocidad > ?)"){
                        if (personaje is Picaro || personaje.velocidad > 10){
                            println("Te cuelas en la habitación exitosamente. No hay nadie, asi que cierras la puerta y aprovechas para descansar")
                            println("Ganas 2 de vida y 2 de experiencia")
                            personaje.vida += 2
                            personaje.experiencia += 2
                        } else{
                            println("No eres capaz de abrir la puerta y el tabernero te descubre. Te echan de la posada")
                            println("Pierdes 2 de vida")
                            personaje.vida -= 2
                        }
                    },
                    Opcion("Colarte en la segunda puerta (Clase pícaro o velocidad > ?)") {
                        if (personaje is Picaro || personaje.velocidad > 10){
                            when(personaje){
                                is Picaro -> {
                                    println("Te encuentras con un picaro durmiendo y ves una reluciente daga de hierro a su lado")
                                    println("Consigues una daga de hierro (3 de ataque y 3 de velocidad) y 2 de experiencia")
                                    personaje.cambiarArma(Arma("Dagas de hierro", bonusAtaque = 3, bonusVelocidad = 3))
                                    personaje.experiencia += 2
                                }
                                is Mago -> {
                                    println("Te encuentras con un mago durmiendo y ves un reluciente báculo de hierro a su lado")
                                    println("Consigues una báculo de hierro (3 de ataque y 3 de maná) y 2 de experiencia")
                                    personaje.cambiarArma(Arma("Báculo de hierro", bonusAtaque = 3, bonusMana = 3))
                                    personaje.experiencia += 2
                                }
                                is Caballero -> {
                                    println("Te encuentras con un caballero durmiendo y ves una reluciente espada de hierro a su lado")
                                    println("Consigues una espada de hierro (3 de ataque y 3 de defensa) y 2 de experiencia")
                                    personaje.cambiarArma(Arma("Espada de hierro", bonusAtaque = 3, bonusDefensa = 3))
                                    personaje.experiencia += 2
                                }
                                is Arquero -> {
                                    println("Te encuentras con un arquero durmiendo y ves una reluciente arco de hierro a su lado")
                                    println("Consigues un arco de hierro (3 de ataque y 3 de velocidad) y 2 de experiencia")
                                    personaje.cambiarArma(Arma("Dagas de hierro", bonusAtaque = 3, bonusVelocidad = 3))
                                    personaje.experiencia += 2
                                }

                            }
                        } else{
                            println("Entras en la habitación pero haces tanto ruido con la puerta, que despiertas a un caballero que hay durmiendo")
                            val caballero = Enemigo("Caballero Somnoliento", vida = 25, ataque = 10, defensa = 8, velocidad = 4)
                            val batalla = Batalla(personaje, caballero)
                            batalla.iniciar()
                            println("Consigues resistir lo ataques del caballero y huyes de la posada")
                            println("Ganas 2 de experiencia")
                            personaje.experiencia += 2
                        }
                    },
                    Opcion("Irse de la posada"){
                        println("Desechas la idea de colarte en la posada y sigues tu camino")
                    }
                )
                subOpciones.forEachIndexed { index, opcion ->
                    println("${index + 1}. ${opcion.descripcion}")
                }
                println("Selecciona una opción:")
                val opcionSeleccionada = readLine()?.toIntOrNull() ?: 1
                subOpciones[opcionSeleccionada - 1].accion(personaje)
            }
            opcionesPuerta(personaje)
        }


    )


)


val eventosFaciles = mutableListOf(eventoMercaderSospechoso, eventoCofreOxidado, posadaBrisavento)
val eventosMedios = mutableListOf<Evento>()
val eventosDificiles = mutableListOf<Evento>()