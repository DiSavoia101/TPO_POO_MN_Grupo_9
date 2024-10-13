package Personajes.Enemigos.Raza;

import Personajes.Clases.Arquero;
import Personajes.Enemigos.Enemigo;
import Personajes.Personaje;

public class Bandido{
    private Arquero clase;

    //TODO
    public Bandido(Arquero tipo) {
        this.clase = tipo;
    }

    public int atacar(){
        return clase.flechazo();
    }

    public int mostrarVida(){
        return clase.mostrarVida();
    }
}
