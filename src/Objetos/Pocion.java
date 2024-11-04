package Objetos;

import Personajes.Personaje;

public class Pocion extends Objeto {

    protected int curacion;

    public Pocion(int valor,String nombre, int curacion) {

        super(valor, nombre);
        this.curacion = curacion;
    }


    public void usar(Personaje personaje){
        int saludActual = personaje.getSalud();

        if(this.curacion + saludActual > personaje.getSaludMaxima()){
            this.curacion = (this.curacion+saludActual) - personaje.getSaludMaxima();
        }
        personaje.curar(this.curacion);
    }

    public int getCuracion() {
        return curacion;
    }

    public String mostrarEnTienda(){
        return (valor +"     | "+ nombre + " " + curacion);
    }
}
