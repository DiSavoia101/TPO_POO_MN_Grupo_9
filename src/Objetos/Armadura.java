package Objetos;

import Personajes.Personaje;

public class Armadura extends Objeto{

    protected int defensa;

    public Armadura(int valor,String nombre, int defensa) {
        super(valor, nombre);
        this.defensa = defensa;
    }

    public void usar(Personaje personaje){

    }

    public int getDefensa() {
        return defensa;
    }
    public String mostrarEnTienda(){
        return (valor +"     | "+ nombre + " +" + defensa + " de defensa");
    }
}
