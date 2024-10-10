package Personajes.Clases;

import Personajes.Personaje;

public class Guerrero extends Personaje {
    public Guerrero(){
        super(100,20,20,10);
    }

    public int Atacar(){
        return 0;
    }

    public int AtaquePesado(){
        return 1;
    }

    public int CortarProfundo(){
        return 2;
    }

    public int Gritar(){
        return 3;
    }
}
