package Mapa;

import Estructuras.Tienda;
import Personajes.Personaje;

import java.util.Objects;
import java.util.Random;


public class Mundo {

    Celda[][] matriz;
    int x;
    int y;
    boolean existePersonaje;
    int jX;
    int jY;
    Random randomNumbers = new Random();
    boolean promocionado = false ;

    public Mundo(int x, int y){
        this.matriz = new Celda[y][x];
        this.x = x;
        this.y = y;
    }

    public void crearMundo(){
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
                if(Objects.equals(matrizAux[i][j].getNombre(), "T")){
                    if((Objects.equals(matrizAux[i - 1][j].getNombre(), "A")) &&
                    (Objects.equals(matrizAux[i + 1][j].getNombre(), "A"))){
                        matrizAux[i][j].setNombre("A");
                    }
                    else if((Objects.equals(matrizAux[i][j - 1].getNombre(), "A")) &&
                            (Objects.equals(matrizAux[i][j + 1].getNombre(), "A"))){
                        matrizAux[i][j].setNombre("A");
                    }
                }
            }
        }
        this.matriz = matrizAux;
        for (int i = 1; i < this.y-1; i++) {
            for (int j = 1; j < this.x-1; j++) {
                if(Objects.equals(matrizAux[i][j].getNombre(), "A")){
                    if((Objects.equals(matrizAux[i - 1][j].getNombre(), "T"))&&(Objects.equals(matrizAux[i + 1][j].getNombre(), "T"))&&
                            (Objects.equals(matrizAux[i][j + 1].getNombre(), "T"))&&(Objects.equals(matrizAux[i][j - 1].getNombre(), "T"))){
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

    public String genRaza(){
        int random = randomNumbers.nextInt( 0,3);
        String raza = switch (random) {
            case 0 -> "Bandido";
            case 1 -> "Zombie";
            case 2 -> "Esqueleto";
            case 3 -> "Animal";
            default -> "";
        };
        return raza;
    }

    public void genMalulazos(int nivelJugador){
        for (int i = 0; i < this.y; i++) {
            for (int j = 0; j < this.x; j++) {
                String nuevo = this.matriz[i][j].getNombre();
                if(!Objects.equals(nuevo, "A") && (this.matriz[i][j].getPersonaje() == null)) {
                    int nivelEnemigo = randomNumbers.nextInt(nivelJugador-1, nivelJugador+3); // Jugador: 1 - 0 → 4
                    Personaje malulo = new Personaje(genRaza(), 30,5,20,(nivelEnemigo <= 0 ? 1 : nivelEnemigo));
                    int valor = randomNumbers.nextInt(10) + 1;
                    if (valor < 2) {
                        this.matriz[i][j].setPersonaje(malulo);
                        this.matriz[i][j].setNombre("MT");
                    }
                }
            }
        }
        genProfe();
    }
    public void genProfe(){
        Personaje profe = new Personaje("Profe", 500, 50,55,10 );
        for (int i = 0; i < 5; i++) {
            for (int j = (x/2)-3; j < (this.x/2)+4; j++) {
                if(( j == x/2 && i == 2)) {
                    this.matriz[i][j].setPersonaje(profe);
                    this.matriz[i][j].setNombre("P");
                }else {
                    this.matriz[i][j].setNombre("T");
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
                            System.out.print("\u001B[42m" + "\u001B[30m" + "M" + ANSI_RESET);
                            break ;
                        case "K":
                            System.out.print("\u001B[42m" + "\u001B[30m" + "T" + ANSI_RESET);
                            break;
                        case "P":
                            System.out.print("\u001B[42m" + "\u001B[30m" + "P" + ANSI_RESET);
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
                    if (moverPersonajeAux(this.jY-1,this.jX, this.jY, this.jX,jugador)){
                        this.jY -= 1;
                    }
                    break;
                case 1: // A
                    if (moverPersonajeAux(this.jY,this.jX-1, this.jY, this.jX,jugador)){
                        this.jX -= 1;
                    }
                    break;
                case 2: // S
                    if (moverPersonajeAux(this.jY+1,this.jX, this.jY, this.jX,jugador)){
                        this.jY += 1;
                    }
                    break;
                case 3: // D
                    if (moverPersonajeAux(this.jY,this.jX+1, this.jY, this.jX,jugador)){
                        this.jX += 1;
                    }
                    break;
                default:
            }
            matriz[jY][jX].setPersonaje(jugador);
            if(!matriz[jY][jX].getPersonaje().isAlive()){
                existePersonaje = false;
                System.out.println("FIN DEL JUEGO");
            }else if(this.promocionado){
                existePersonaje = false;
                System.out.println("Gansate nazi promocionaste");
            }
        }
    }

    private boolean moverPersonajeAux(int columnaNueva, int filaNueva, int columnaVieja, int filaVieja, Personaje jugador){
        boolean huboMovimiento = false;
        if (!(columnaNueva == y || filaNueva == x || columnaNueva == -1 || filaNueva == -1)){ // Verifico que no me este moviendo a un borde
            if (matriz[columnaNueva][filaNueva].getNombre().equals("T")) {
                matriz[columnaVieja][filaVieja].setNombre("T");
                matriz[columnaNueva][filaNueva].setNombre("TP");
                huboMovimiento = true;
            }else if ((matriz[columnaNueva][filaNueva].getNombre().equals("A"))){
                huboMovimiento = false;
            } else if (matriz[columnaNueva][filaNueva].getNombre().equals("MT")) {
                jugador.pelear(this.matriz[columnaNueva][filaNueva].getPersonaje());
                if (!this.matriz[columnaNueva][filaNueva].getPersonaje().isAlive()) {
                    matriz[columnaVieja][filaVieja].setNombre("T");
                    matriz[columnaNueva][filaNueva].setNombre("TP");
                    huboMovimiento = true;
                    if (this.matriz[columnaNueva][filaNueva].getPersonaje().getNombre().equals("Profe")){
                        this.promocionado  = true;
                    }
                    jugador.recibirExp(this.matriz[columnaNueva][filaNueva].getPersonaje().getNivel());
                }
            } else if (matriz[columnaNueva][filaNueva].getNombre().equals("K")) {
                matriz[columnaNueva][filaNueva].getTienda().mostrarObjetos(jugador);
            }
        }
        return huboMovimiento;
    }

    public void genTiendaDePrueba(){
        Tienda tienda = new Tienda();
        this.matriz[jY][jX+3].setPersonaje(null);
        this.matriz[jY][jX+3].setTienda(tienda);
        this.matriz[jY][jX+3].setNombre("K");
    }

}
