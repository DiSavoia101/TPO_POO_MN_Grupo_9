package Personajes.Enemigos;

import Personajes.Clases.Arquero;
import Personajes.Personaje;

public class Enemigo extends Personaje {
    protected Arquero clase;

    public Enemigo(Arquero clasePj) {
        this.clase = clasePj;
    }
}
