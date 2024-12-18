package Objetos;

import Personajes.Personaje;

public class Pocion extends Objeto {

    protected int curacion;

    public Pocion(int valor,String nombre, String descripcion, int curacion) {

        super(valor, nombre, descripcion);
        this.curacion = curacion;
    }


    public void usar(Personaje personaje){
        int saludActual = personaje.getSalud();

        if(this.curacion + saludActual > personaje.getSaludMaxima()){
            this.curacion = (this.curacion+saludActual) - personaje.getSaludMaxima();
            System.out.printf("%s se ha usado %s y se ha curado %s puntos de vida", personaje.getNombre(), this.nombre, this.curacion);
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
