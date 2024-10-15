import Mapa.Mundo;
import Personajes.Clases.Arquero;
import Personajes.Clases.Guerrero;
import Personajes.Enemigos.Raza.Bandido;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Mundo mundo = new Mundo(50,50);
        mundo.crearMundo();
        mundo.mostrarMundo();
//        mundo.mostrarMundo();
//        Arquero arquero = new Arquero();
//        Bandido band = new Bandido(arquero);
//        System.out.println(band.atacar());
//        System.out.println(band.mostrarVida());
    }



}
