package Personajes.Clases;

import Personajes.Personaje;

public class Mago extends Personaje {
    public Mago() {
        super(50, 40, 5, 20);
    }

    public int BolaFuego(){
        return 0;
    }

    public int Lanzallama(){
        return 1;
    }

    public int Dragon(){
        return 2;
    }

    public int MuroLlamas(){
        return 3;
    }
}
