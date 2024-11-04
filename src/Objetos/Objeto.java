package Objetos;
import Personajes.Personaje;

public abstract class Objeto {

    protected String nombre;
    protected int valor;



    public Objeto(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public abstract void usar(Personaje personaje);


    public String getNombre(){
        return nombre;
    }
    public int getValor(){
        return valor;
    }

    public abstract String mostrarEnTienda();


}
