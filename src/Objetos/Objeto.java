package Objetos;
import Personajes.Personaje;

public abstract class Objeto {

    protected String nombre;
    protected int valor;
    protected String descripcion;



    public Objeto(int valor, String nombre, String descripcion) {
        this.valor = valor;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void usar(Personaje personaje);


    public String getNombre(){
        return nombre;
    }
    public int getValor(){
        return valor;
    }

    public abstract String mostrarEnTienda();

    public String getDescripcion() {
        return descripcion;
    }
}
