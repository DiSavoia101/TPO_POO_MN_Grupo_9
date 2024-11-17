package Objetos;

import Personajes.Personaje;

public class Armadura extends Objeto{
    protected int defensa;

    public Armadura(int valor,String nombre, int defensa, String descripcion) {
        super(valor, nombre, descripcion);
        this.defensa = defensa;
    }

    public void usar(Personaje personaje){
        personaje.setPntosDefenza(personaje.getPntosDefenza() + this.defensa);
        System.out.printf("¡%s se ha equipado %s con éxito y aumento en %s su defenza!\n", personaje.getNombre(), this.nombre, this.defensa);
    }

    public int getDefensa() {
        return defensa;
    }

    public String mostrarEnTienda(){
        return (valor +"     | "+ nombre + " +" + defensa + " de defensa");
    }
}
