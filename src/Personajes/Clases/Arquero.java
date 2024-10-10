package Personajes.Clases;

import Personajes.Personaje;

public class Arquero extends Personaje {
    public Arquero() {
        super(60, 30, 10, 55);
    }

    public int flechazo(){
        return 0;
    }
    public int flechaVeneno(){
        return 1;
    }

    public int lluviaDeFlechas(){
        return 2;

    }

    public int Evasion(){
        return 3;
    }

}
