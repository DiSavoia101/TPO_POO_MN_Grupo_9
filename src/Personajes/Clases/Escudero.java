package Personajes.Clases;

import Personajes.Personaje;

public class Escudero extends Personaje {
    public Escudero() {
        super(120, 10, 40, 5);
    }

    public int Atacar(){
        return this.pntosAtaque;
    }

    public int Empujar(){
        return 1;
    }

    public int Fortalecer(){
        return 2;
    }

    public int Bloquear(){
        return 3;
    }
}
