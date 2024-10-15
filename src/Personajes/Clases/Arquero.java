package Personajes.Clases;

import Personajes.Personaje;

public class Arquero extends Personaje {
    public Arquero() {
        super(60, 25, 10, 55);
    }

    public int hab1(){
        return this.pntosAtaque + 10;
    }
    public int hab2(){
        return 1;
    }

    public int hab3(){
        return 2;
    }

    public int hab4(){
        return 3;
    }

}
