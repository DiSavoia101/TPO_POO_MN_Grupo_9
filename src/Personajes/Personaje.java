package Personajes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Personaje {
    protected String nombre;
    protected String clase;
    protected String estado = " ";
    protected int salud;
    protected int experiencia;
    protected int dinero;
    protected int nivel;
    protected int xpMAX;
    protected int esquiva;
    protected int pntosDefenza;
    protected Personaje rival;
    protected ArrayList<Habilidad> habilidades;
    Scanner lector = new Scanner(System.in);


    public Personaje(String nombre, String clase, int saludPj, int pntosDefenzaPj, int esquivaPj, int nivelPj){
        this.nombre = nombre;
        this.clase = clase;
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.pntosDefenza = pntosDefenzaPj;
        this.habilidades = new ArrayList<Habilidad>();
        this.nivel = nivelPj;
        genHabilidades();
        nivelarPersonaje();
    }

    public Personaje(String nombre, int saludPj, int pntosDefenzaPj, int esquivaPj, int nivelPj){
        this.nombre = nombre;
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.pntosDefenza = pntosDefenzaPj;
        this.habilidades = new ArrayList<Habilidad>();
        this.nivel = nivelPj;
        genHabilidades();
        nivelarPersonaje();
    }
    public Personaje(){}


    public void recibirDanio(Habilidad hab){
        //logica de tu defensa
        int danio = hab.getDanio();
        if (danio > this.pntosDefenza) {
            salud -= (danio - this.pntosDefenza);
        }
        if (this.estado == " ") {
            if (hab.efecto != null) {
                this.estado = hab.getEfecto();
            }
        } else {
            salud -= 3;
        }
        if (this.isAlive() && this.rival.isAlive()){
            if (this.nombre.equals("Bandido")) {
                habRandom();
            } else {
                mostrarHab();
            }
        }
    }

    public void habRandom(){
        Random randomNumbers = new Random();
        int ataque = randomNumbers.nextInt(2);
        System.out.println("El" + this.nombre + " utiliza " + habilidades.get(ataque).getNombre());
        this.rival.recibirDanio(this.habilidades.get(ataque));

    }


    public Personaje getRival(){
        return this.rival;
    }

    public void setRival(Personaje malulo){
        this.rival = malulo;
    }

    public void pelear(Personaje malulo){
        this.rival = malulo;
        this.rival.setRival(this);
        for (int i = 0; i < 20; i++){
            System.out.println("\n");
        }
        System.out.println("EMPIEZA EL COMBATE!");
        while (rival.isAlive() && this.isAlive()){
            menuPelea();
        }
    }

    public void menuPelea(){
        System.out.println("——————————————————————————————");
        System.out.println(" ¿ Qué te gustaría hacer ?");
        System.out.println("——————————————————————————————");
        System.out.println("1 - Usar habilidad");
        System.out.println("2 - Usar objeto");
        System.out.println("——————————————————————————————");


        String opcion = this.lector.next();
        switch (opcion){
            case "1":
                mostrarHab();

                break;
            case "2":
                // TODO
                System.out.println("más sarasa");

                break;
            default:
                System.out.println("le erraste");
                menuPelea();
                break;
        }

    }
    public void mostrarHab(){
        System.out.printf("%s:%s %s\n", this.rival.getNombre(), this.rival.mostrarVida(),this.rival.estado);
        for(int i = 0; i < habilidades.size(); i++){
            System.out.println(i+1 + " - " + habilidades.get(i).getNombre());
        }
        System.out.printf("%s:%s %s\n", this.getNombre(), this.mostrarVida(),this.estado);
        String opcion = this.lector.next();
        if(parseInt(opcion) <= habilidades.size()){
            this.rival.recibirDanio(habilidades.get(parseInt(opcion)-1));
        }
        else {
            System.out.println("Opción Incorrecta");
            mostrarHab();
        }


    }
    public void nivelarPersonaje(){
        for(int i = 0; i < this.habilidades.size(); i++){
            int danio = this.habilidades.get(i).getDanio();
            this.habilidades.get(i).setDanio(danio * (this.nivel/2));
        }
    }
    public void genHabilidades() {
        Habilidad hab2 = null;
        Habilidad hab1 = null;
        if(this.nombre == "Bandido"){
            Random randomNumbers = new Random();
            int claseRand = randomNumbers.nextInt(3)+1;
            switch (claseRand){
                case 1:
                    this.clase = "Arquero";
                default:
                    this.clase  = "Arquero";
            }

        }
        if (this.clase == "Arquero") {
            hab1 = new Habilidad("Flechazo", 10, null);
            hab2 = new Habilidad("Flecha Veneno", 3, "Veneno");

        }
        this.habilidades.add(hab1);
        this.habilidades.add(hab2);
    }

    public boolean isAlive(){
        return this.salud>0;
    }
    public String getNombre(){
        return this.nombre;

    }

    public int mostrarVida(){
        return this.salud;
    }
}
