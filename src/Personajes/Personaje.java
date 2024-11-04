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
    protected int saludMaxima;
    protected int experiencia;
    protected int dinero;
    protected int nivel;
    protected int expMAX;
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
            System.out.printf("%s recibió %s de daño\n", this.getNombre(), (danio - this.pntosDefenza));
        }
        if (this.estado == " ") {
            if (hab.efecto != null) {
                this.estado = hab.getEfecto();
                System.out.printf("%s adquirió el estado: %s\n", this.getNombre(), hab.efecto);
            }
        } else {
            salud -= 3;
            System.out.printf("%s recibio 3 de daño por veneno\n", this.getNombre());
        }
        if (this.isAlive() && this.rival.isAlive()){
            if (this.nombre.equals("Bandido")) {
                habRandom();
            } else {
                mostrarHab();
            }
        }
        else {
            if (!this.isAlive()){
                System.out.printf("%s ha muerto, el combate ha terminado\n", this.getNombre());
            }
        }
    }

    public void habRandom(){
        Random randomNumbers = new Random();
        int ataque = randomNumbers.nextInt(2);
        System.out.printf("El %s utiliza %s\n", this.nombre, habilidades.get(ataque).getNombre());
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
        System.out.printf("¡Ha aparecido un %s con intenciones hostiles!\n", this.rival.getNombre());
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
        System.out.println("——————————————————————————————");
        System.out.printf("Rival: %s, Salud: %s, Estado: %s, Su nivel es: %s\n", this.rival.getNombre(), this.rival.mostrarVida(),(this.rival.estado.isBlank() ? "Normal" : this.rival.estado), this.rival.getNivel());
        for(int i = 0; i < habilidades.size(); i++){
            System.out.println(i+1 + " - " + habilidades.get(i).getNombre());
        }
        System.out.printf("Tu nombre: %s, Salud: %s, Estado: %s, Tu nivel es: %s\n", this.getNombre(), this.mostrarVida(),(this.estado.isBlank() ? "Normal" : this.estado), this.getNivel());
        System.out.println("——————————————————————————————");
        System.out.println("¿Qué habilidad quieres usar? ");
        String opcion = this.lector.next();
        System.out.println("——————————————————————————————");
        if(parseInt(opcion) <= habilidades.size()){
            System.out.printf("%s utiliza %s\n", this.getNombre(), habilidades.get(parseInt(opcion)-1).getNombre());
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
            this.setPntosDefenza(this.getPntosDefenza() * (this.nivel/2));
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
            hab2 = new Habilidad("Flecha Veneno", 3, "Envenenad@");

        }
        this.habilidades.add(hab1);
        this.habilidades.add(hab2);
    }

    public void recibirExp(int nivelEnemigo){
        int expRecibida = nivelEnemigo*2;
        if (this.experiencia + expRecibida >= expMAX){
            this.experiencia = (this.experiencia + expRecibida - expMAX);
            this.nivel+=1;
            this.expMAX += this.nivel * 10;
            this.nivelarPersonaje();
            System.out.printf("¡%s ha subido a nivel %s!\n", this.nombre, this.nivel);
        }
        else {
            this.experiencia += nivelEnemigo*10;
        }
        System.out.printf("Experiencia: %s de %s\n", this.experiencia, this.expMAX);
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

    public int getNivel(){
        return this.nivel;
    }

    public int getPntosDefenza(){
        return this.pntosDefenza;
    }

    public void setPntosDefenza(int nuevaDefenza){
        this.pntosDefenza = nuevaDefenza;
    }
}
