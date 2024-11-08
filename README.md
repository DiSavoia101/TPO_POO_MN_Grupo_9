# El Equipo

Este proyecto esta siendo creado, administrado y gestionado por el ***Equipo 9***.

# El proyecto

Decidimos realizar nuestro trabajo practico de **Paradigma Orientado a Objetos** en base al genero de juegos ***RPG - "Role Playing Game"***.

>RPG: Los jugadores crean o eligen personajes con habilidades y caracteristicas especificas, que influyen en como interactuan con el entorno y con otros personajes. 
A medida que avanza la historia, los jugadores enfrentan dasafios que requieren de la toma de decisiones.
_Ejemplo: Dungeons & Dragons; Final Fantasy: Mass Effect._

### Dinamica del Negocio
Te adentras a esta realidad como un alumno de Paradigma Orientado a Objetos, el cual busca lograr la promoción de la meteria. Para ello, debes combatir contra los _Bandidos_; avanzando entre los niveles hasta llegar al jefe, ***“El Profesor”***, con el objetivo de que te promocione.

# Terminologia Utilizada

- ***Clase***: Define el conjunto de habilidades que posee el jugador.
- ***Habilidad***: Conjunto de características/ acciones que posee el jugador para realizar contra el enemigo.
- ***Estado***: Condicion particular que tenga el personaje temporalmente debido al ataque. (EJ: envenenado)
- ***Malulo***: Enemigo. Bandido/Esqueleto/No muerto/Animal, con los cual te deberás enfrentar.
- ***Salud***: Cantidad de puntos de vida que posee un personaje, representa la cantidad de ataques que puede recibir.
- ***Punto de Defensa***: Valor que representa la resistencia ante un ataque.
- ***Esquiva***: vValor que representa la probabilidad de esquivar un ataque.
- ***Experiencia***: Valor que representa el progreso del jugador en el juego, se reinicia al acabar la partida.
- ***Expriencia máxima***: Valor de experiencia que se debe alcanzar para que el jugador avance de nivel.
- ***Nivel***: Valor que representa la experiencia acumulada por un personaje a lo largo del juego.
- ***Objeto***: Elementos que el jugador puede utilizar a lo largo del juego adquiriéndolos desde una tienda.
- ***Controlador***: Parte del código que se encarga de generar la partida.
- ***Mundo***: Terreno en el cual se lleva a cabo una partida, cambia según cada partida.
- ***Celda***: Unidad que compone el mundo y representa el tipo de terreno de un lugar en particular.

# Requerimientos de Usuario

- El ***usuario debe*** tener un personaje que lo represente a lo largo del juego.
- El ***usuario debe*** poder elegir entre varias clases para crear su personaje (escudero, guerrero, mago, arquero).
- El ***usuario debe*** poder ver el mapa donde se desarrolla el juego.
- El ***usuario debe*** poder recorrer el mapa.
- El ***usuario debe*** poder verse en el mapa.
- El ***usuario debe*** poder ver a los enemigos en el mapa.
- El ***usuario debe*** poder entablar combates con el enemigo y poder defenderse el mismo.
- El ***usuario debe*** poder acceder a las habilidades propias de su clase.
- El ***usuario debe*** poder subir de nivel según su experiencia adquirida en combate.
- El ***usuario debe*** poder ver tiendas en el mapa.
- El ***usuario debe*** poder utilizar los objetos adquiridos de la tienda en combate.
- El ***usuario debe*** poder tener un elemento que le permita realizar intercambios en las tiendas del juego
- El ***usuario debe*** poder adquirir elementos de la tienda.
- El ***usuario debe*** poder ganar/perder un combate.
- El ***usuario debe*** poder finalizar la partida.

# Funcionalidades

- Generar un Mundo con distintos terrenos (Tierra/Agua/Bosque/Tienda)
- Generar un personaje Jugador 
- Generar Malulos 
- Sistema de movimiento para el jugador en el mapa
- Sistema de colisiones en el mapa
- Sistema de combate (atacar/recibir daño)
- Sistema de habilidades durante el combate (según clase de los personajes)
- Sistema enemigo
- Sistema para utilizar objetos durante el combate
- Sistema para adquirir objetos en tiendas
- Sistema para visualizar el mapa
- Sistema para visualizar el combate
