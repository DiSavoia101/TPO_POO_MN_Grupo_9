package Personajes.Enemigos.Raza;

import Personajes.Clases.Guerrero;

public class Esqueleto {
    private Guerrero clase;


    public Esqueleto(Guerrero tipo){this.clase = tipo;}

    public int atacar(){
        return clase.atacar();
    }

    public int mostrarVida(){
        return clase.mostrarVida();
    }



}
