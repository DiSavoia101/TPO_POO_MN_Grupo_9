package Personajes;

public class Habilidad {
    protected String nombre;
    protected int danio;
    protected String efecto;
    protected String descripcion;


    public Habilidad(String nombre, int danio, String efecto, String descripcion) {
        this.nombre = nombre;
        this.danio = danio;
        this.efecto = efecto;
        this.descripcion = descripcion;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getDanio(){
        return this.danio;
    }

    public void setDanio(int danio){
        this.danio = danio;
    }

    public String getEfecto(){
        return this.efecto;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
}
