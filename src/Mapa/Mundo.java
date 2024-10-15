package Mapa;

import java.lang.reflect.Array;
import java.util.Random;


public class Mundo {

    Celda[][] matriz;
    int x;
    int y;


    public Mundo(int x, int y){
        this.matriz = new Celda[x][y];
        this.x = x;
        this.y = y;

    }

    public void crearMundo(){
        Random randomNumbers = new Random();
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                int valor = randomNumbers.nextInt(9)+1;
                switch (valor){
                    case 1:
                    case 2:
                        this.matriz[i][j] = new Celda("A");
                        break;
                    default:
                        this.matriz[i][j] = new Celda("T");

                }
            }
        }

        fixeoDeMundo();

    }

    public void fixeoDeMundo(){
        Celda[][] matrizAux = this.matriz;
        for (int i = 1; i < this.y - 1; i++) {
            for (int j = 1; j < this.x - 1; j++) {
                if(matrizAux[i][j].getNombre() == "T"){
                    if((matrizAux[i-1][j].getNombre() == "A") &&
                    (matrizAux[i+1][j].getNombre() == "A")){
                        matrizAux[i][j].setNombre("A");
                    }
                    if((matrizAux[i][j-1].getNombre() == "A") &&
                            (matrizAux[i][j+1].getNombre() == "A")){
                        matrizAux[i][j].setNombre("A");
                    }
                }
            }
        }
        this.matriz = matrizAux;
        for (int i = 1; i < this.y-1; i++) {
            for (int j = 1; j < this.x-1; j++) {
                if(matrizAux[i][j].getNombre() == "A"){
                    if((matrizAux[i-1][j].getNombre() == "T")&&(matrizAux[i+1][j].getNombre() == "T")&&
                            (matrizAux[i][j+1].getNombre() == "T")&&(matrizAux[i][j-1].getNombre() == "T")){
                        this.matriz[i][j].setNombre("T");
                    }
                }
            }
        }
    }

    public void mostrarMundo(){
        String ANSI_RESET = "\u001B[0m";
        for (int i = 0; i < this.matriz.length; i++) {
            System.out.println("");
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (matriz[i][j].getNombre() == "A"){
                    System.out.print("\u001B[44m" + " "+ANSI_RESET);
                }else if(matriz[i][j].getNombre() == "T"){
                    System.out.print("\u001B[42m" + " " + ANSI_RESET);
                }

            }
        }
    }

}
