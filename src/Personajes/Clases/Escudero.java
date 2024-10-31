package Personajes.Clases;

import Personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Escudero extends Personaje {
    public Escudero() {
//        super(120, 10, 40, 5);
    }

    public List<Integer> Empujar(){
        List<Integer> estados = new ArrayList<>(2);
        estados.add(1);
        estados.add(1);
        return estados;
    }

    public int Fortalecer(){
        return 2;
    }

    public int Bloquear(){
        return 3;
    }
}
