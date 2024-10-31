package Mapa;

import Personajes.Personaje;

public class Celda {
    private String nombre;
    private Personaje personaje;

    public Celda(String nombre) {
        this.nombre = nombre;
    }
    public Celda(String nombre, Personaje personaje) {
        this.nombre = nombre;
        this.personaje = personaje;
    }
    public Personaje getPersonaje() {
        return personaje;
    }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
