package Mapa;

import Personajes.Personaje;

import java.util.Random;


public class Mundo {

    Celda[][] matriz;
    int x;
    int y;
    boolean existePersonaje;
    int jX;
    int jY;


    public Mundo(int x, int y){
        this.matriz = new Celda[y][x];
        this.x = x;
        this.y = y;

    }

    public void crearMundo(){
        Random randomNumbers = new Random();
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
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
                    else if((matrizAux[i][j-1].getNombre() == "A") &&
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

    public void generarJugador(Personaje personaje){
        for (int i = (y/2)-3; i < (this.y/2)+4; i++) {
            for (int j = (x/2)-3; j < (this.x/2)+4; j++) {
                if((i==y/2) &&( j == x/2)) {
                    this.jX = j;
                    this.jY = i;
                    this.matriz[i][j].setPersonaje(personaje);
                    this.matriz[i][j].setNombre("TP");
                }else {
                    this.matriz[i][j].setNombre("T");
                }
            }


        }
        fixeoDeMundo();
        this.existePersonaje = true;
    }

    public void genMalulazos(){
        Random randomNumbers = new Random();
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
                String nuevo = this.matriz[i][j].getNombre();
                if(nuevo != "A") {
                    Personaje malulo = new Personaje("Bandido", 30,8,20,4);
                    int valor = randomNumbers.nextInt(10) + 1;
                    if (valor < 2) {
                        this.matriz[i][j].setPersonaje(malulo);
                        this.matriz[i][j].setNombre("MT");

                    }
                }

            }
        }
    }

    public void mostrarMundo(){
        String ANSI_RESET = "\u001B[0m";
        if(existePersonaje) {
            for (int i = 0; i < this.y; i++) {
                System.out.println("");
                for (int j = 0; j < this.x; j++) {
                    switch (matriz[i][j].getNombre()) {
                        case "A":
                            System.out.print("\u001B[44m" + " " + ANSI_RESET);
                            break;
                        case "T":
                            System.out.print("\u001B[42m" + " " + ANSI_RESET);
                            break;
                        case "TP":
                            System.out.print("\u001B[42m" + "\u001B[30m" + "O" + ANSI_RESET);
                            break;
                        case "MT":
                            System.out.print("\u001B[42m" + "\u001B[30m" + "B" + ANSI_RESET);
                            break;

                        default:
                    }
                }
            }
        }
    }

    public void moverPersonaje(int dir) {
        if (this.existePersonaje){
            Personaje jugador = matriz[jY][jX].getPersonaje();
            matriz[jY][jX].setPersonaje(null);
            switch (dir){
                case 0: // W
                    if (matriz[jY-1][jX].getNombre().equals("T")) {
                        matriz[jY][jX].setNombre("T");
                        matriz[jY-1][jX].setNombre("TP");
                        this.jY -= 1;

                    }else if(matriz[jY-1][jX].getNombre().equals("MT")){
                        jugador.pelear(this.matriz[jY-1][jX].getPersonaje());
                        if (!this.matriz[jY-1][jX].getPersonaje().isAlive()){
                            matriz[jY][jX].setNombre("T");
                            matriz[jY-1][jX].setNombre("TP");
                            this.jY -= 1;
                        }
                    }
                    break;
                case 1: // A
                    if (matriz[jY][jX-1].getNombre().equals("T")) {
                        matriz[jY][jX].setNombre("T");
                        matriz[jY][jX-1].setNombre("TP");
                        this.jX -= 1;
                    }else if(matriz[jY][jX-1].getNombre().equals("MT")){
                        jugador.pelear(this.matriz[jY][jX-1].getPersonaje());
                        if (!this.matriz[jY][jX-1].getPersonaje().isAlive()){
                            matriz[jY][jX].setNombre("T");
                            matriz[jY][jX-1].setNombre("TP");
                            this.jX -= 1;
                        }
                    }
                    break;
                case 2: // S
                    if (matriz[jY+1][jX].getNombre().equals("T")) {
                        matriz[jY][jX].setNombre("T");
                        matriz[jY+1][jX].setNombre("TP");
                        this.jY += 1;
                    }else if(matriz[jY+1][jX].getNombre().equals("MT")){
                        jugador.pelear(this.matriz[jY+1][jX].getPersonaje());
                        if (!this.matriz[jY+1][jX].getPersonaje().isAlive()){
                            matriz[jY][jX].setNombre("T");
                            matriz[jY+1][jX].setNombre("TP");
                            this.jY += 1;
                        }
                    }
                    break;
                case 3: // D
                    if (matriz[jY][jX+1].getNombre().equals("T")) {
                        matriz[jY][jX].setNombre("T");
                        matriz[jY][jX+1].setNombre("TP");
                        this.jX += 1;
                    }else if(matriz[jY][jX+1].getNombre().equals("MT")){
                        jugador.pelear(this.matriz[jY][jX+1].getPersonaje());
                        if (!this.matriz[jY][jX+1].getPersonaje().isAlive()){
                            matriz[jY][jX].setNombre("T");
                            matriz[jY][jX+1].setNombre("TP");
                            this.jX += 1;
                        }
                    }
                    break;
                default:
            }
            matriz[jY][jX].setPersonaje(jugador);
            if(!matriz[jY][jX].getPersonaje().isAlive()){
                existePersonaje = false;
                System.out.println("te moriste");
            }

        }
    }
}
