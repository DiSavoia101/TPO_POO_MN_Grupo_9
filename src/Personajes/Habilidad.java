package Personajes;

public class Habilidad {
    protected String nombre;
    protected int danio;
    protected String efecto;


    public Habilidad(String nombre, int danio, String efecto) {
        this.nombre = nombre;
        this.danio = danio;
        this.efecto = efecto;
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
}
