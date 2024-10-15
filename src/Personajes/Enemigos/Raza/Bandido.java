package Personajes.Enemigos.Raza;

import Personajes.Clases.Arquero;
import Personajes.Enemigos.Enemigo;
import java.util.Random;

public class Bandido{
    private Arquero clase;

    //TODO
    public Bandido(Arquero tipo) {
        this.clase = tipo;
    }

    public int atacar(){
        int retorneo=0;
        Random randomNumbers = new Random();
        int ataque = randomNumbers.nextInt(3)+1;
        if (ataque == 1){retorneo = clase.hab1();}
        if (ataque == 2){retorneo = clase.hab2();}
        if (ataque == 3){retorneo = clase.hab3();}
        if (ataque == 4){retorneo = clase.hab4();}
        return retorneo;
    }

    public int mostrarVida(){
        return clase.mostrarVida();
    }
}
