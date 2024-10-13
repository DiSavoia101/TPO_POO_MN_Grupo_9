import Personajes.Clases.Arquero;
import Personajes.Enemigos.Raza.Bandido;

public class Main {
    public static void main(String[] args) {
        Arquero arquero = new Arquero();
        Bandido band = new Bandido(arquero);
        System.out.println(band.atacar());
        System.out.println(band.mostrarVida());
    }
}
