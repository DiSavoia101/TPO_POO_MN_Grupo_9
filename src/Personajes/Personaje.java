package Personajes;

import Objetos.Objeto;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Personaje {
    protected String nombre;
    protected String clase;
    protected String estado = " ";
    protected int duracionEfecto;
    protected int salud;
    protected int saludMaxima;
    protected int experiencia;
    protected int expMAX;
    protected int nivel;
    protected int pntosDefenza;
    protected float potenciador;
    protected Personaje rival; // Información del enemigo contra el que estoy peleando
    protected ArrayList<Habilidad> habilidades;
    protected ArrayList<Habilidad> habilidadesDeClase;
    protected ArrayList<Objeto> inventario;
    protected boolean esEnemigo;
    protected int monedas;
    Scanner lector = new Scanner(System.in);
    Random randomNumbers = new Random();

    public Personaje(String nombre, String clase, int saludPj, int pntosDefenzaPj, int nivelPj){
        this.nombre = nombre;
        this.clase = clase;
        this.salud = saludPj;
        this.saludMaxima = saludPj;
        this.pntosDefenza = pntosDefenzaPj;
        this.potenciador = 1.25f;
        this.habilidades = new ArrayList<Habilidad>();
        this.habilidadesDeClase = new ArrayList<Habilidad>();
        this.inventario = new ArrayList<Objeto>();
        this.nivel = nivelPj;
        this.monedas = 80;
        this.esEnemigo = false;
        genHabilidades();
        nivelarPersonaje();
    }

    public Personaje(String nombre, int saludPj, int pntosDefenzaPj, int nivelPj){
        this.nombre = nombre;
        this.salud = saludPj;
        this.saludMaxima = saludPj;
        this.esEnemigo = true;
        this.potenciador = 1;
        this.pntosDefenza = pntosDefenzaPj;
        this.habilidades = new ArrayList<Habilidad>();
        this.habilidadesDeClase = new ArrayList<Habilidad>();
        this.nivel = nivelPj;
        if (this.nombre.equals("Profe")) {
            genHabProfe();
        }else {
            genHabilidades();

            nivelarPersonaje();
        }
    }
    public void genHabProfe(){
        Habilidad hab1 = new Habilidad("Poner 2", 30, "");
        Habilidad hab2 = new Habilidad("Poner 3.49", 50, "Depresion");
        Habilidad hab3 = new Habilidad("Sancionado", 25, "");
        Habilidad hab4 = new Habilidad("Trompada", 40, "");

        this.habilidades.add(hab1);
        this.habilidades.add(hab2);
        this.habilidades.add(hab3);
        this.habilidades.add(hab4);
    }

    public void recibirDanio(Habilidad hab){
        //logica de tu defensa
        if (hab != null){
            int danio = hab.getDanio();
            danio = !this.esEnemigo ? (int) (danio*this.potenciador) : danio;
            if (danio > this.pntosDefenza) { // 5 def, 10 daño. salud - 5
                salud -= danio - this.pntosDefenza;
                System.out.printf("%s recibió %s de daño\n", this.getNombre(), (danio - this.pntosDefenza));
            }

            if (hab.efecto.equals("Envenenad@") || hab.efecto.equals("Quemadura") || hab.efecto.equals("Sangrando") || hab.efecto.equals("Depresion") || this.duracionEfecto > 0){
                if (this.duracionEfecto > 0){
                    this.duracionEfecto -= 1;
                }
                else {
                    this.duracionEfecto = 3;
                    System.out.printf("%s ha adquirido el estado '%s'\n", this.getNombre(), hab.efecto);
                    this.estado = hab.efecto;
                }
                int danioEfecto = this.saludMaxima/10;
                salud -= danioEfecto;
                System.out.printf("%s recibió %s de daño por el estado '%s'\n", this.getNombre(), danioEfecto, this.estado);
            }
        }
        if (this.isAlive() && this.rival.isAlive()) {

            if (hab != null && (hab.efecto.equals("Bloquead@") || hab.efecto.equals("Evasion"))) {
                System.out.printf("%s no puede atacar debido al estado '%s'\n", this.getNombre(), hab.efecto);
            } else {
                if (this.esEnemigo) {
                    habRandom();
                } else {
                    menuPelea();
                }
            }
        } else if (!this.isAlive()) {
            System.out.printf("%s ha muerto, el combate ha terminado\n", this.getNombre());
            System.out.println("——————————————————————————————");
        }
    }

    public void habRandom(){
        int ataque = randomNumbers.nextInt(this.habilidades.size());
        System.out.printf("El %s utiliza %s\n", this.nombre, habilidades.get(ataque).getNombre());
        this.rival.recibirDanio(this.habilidades.get(ataque));
    }


    public void pelear(Personaje malulo){
        this.rival = malulo;
        int nivelEnemigo = randomNumbers.nextInt(this.nivel-1, this.nivel+3);
        this.rival.setNivel(nivelEnemigo <= 0 ? 1 : nivelEnemigo);
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

                if(!inventario.isEmpty()){
                    mostrarInventario();
                }else{
                    System.out.println("Tenes el invetario vacio");
                }
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

    public void mostrarInventario(){
        System.out.println("——————————————————————————————");
        System.out.println("Inventario");
        for(int i = 0; i < inventario.size(); i++){
            System.out.println(i+1 + " - " + inventario.get(i).getNombre());
        } System.out.printf("Tu nombre: %s, Salud: %s, Estado: %s, Tu nivel es: %s\n", this.getNombre(), this.mostrarVida(),(this.estado.isBlank() ? "Normal" : this.estado), this.getNivel());
        System.out.println("——————————————————————————————");
        System.out.println("¿Que objeto quieres usar?");
        String opcion = this.lector.next();
        System.out.println("——————————————————————————————");
        if(parseInt(opcion) <= inventario.size()){
            System.out.printf("%s utiliza %s\n", this.getNombre(), inventario.get(parseInt(opcion)-1).getNombre());
            inventario.get(parseInt(opcion)-1).usar(this);
            inventario.remove(parseInt(opcion)-1);
            this.rival.recibirDanio(null);
        }else {
            System.out.println("Opción Incorrecta");
            mostrarInventario();
        }
    }

    public void nivelarPersonaje(){
        boolean desbloqueo = false;
        if (this.nivel >= 2 && this.habilidades.size() < 2){
            this.habilidades.add(habilidadesDeClase.getFirst());
            desbloqueo = true;
        }
        if (this.nivel >= 4 && this.habilidades.size() < 3){
            this.habilidades.add(habilidadesDeClase.get(1));
            desbloqueo = true;
        }
        if (this.nivel >= 8 && this.habilidades.size() < 4){
            this.habilidades.add(habilidadesDeClase.get(2));
            desbloqueo = true;
        }
        if (desbloqueo && !this.esEnemigo){
            System.out.printf("¡%s ha conseguido la habilidad '%s'!\n", this.nombre, this.habilidades.getLast().getNombre());
        }
        this.saludMaxima = saludMaxima + (this.nivel*2);
        this.salud = this.saludMaxima;
        this.setPntosDefenza(this.getPntosDefenza() + this.nivel/2);
        for (Habilidad habilidad : this.habilidades) {
            int danio = habilidad.getDanio();
            habilidad.setDanio(danio + this.nivel / 2);
        }
    }

    public void genHabilidades() {
        Habilidad hab1 = null;
        Habilidad hab2 = null;
        Habilidad hab3 = null;
        Habilidad hab4 = null;
        if(this.esEnemigo){
            int claseRand = randomNumbers.nextInt(4)+1;
            switch (claseRand){
                case 1:
                    this.clase = "Arquero";
                    break;
                case 2:
                    this.clase = "Escudero";
                    break;
                case 3:
                    this.clase = "Mago";
                    break;
                case 4:
                    this.clase = "Guerrero";
                    break;
                default:
            }
        }
        switch (this.clase){
            case "Arquero":
                hab1 = new Habilidad("Flechazo", 8, "");
                hab2 = new Habilidad("Flecha Veneno", 6, "Envenenad@");
                hab3 = new Habilidad("Lluvia de flechas", 12, "");
                hab4 = new Habilidad("Evasion", 0, "Evasion");
                break;
            case "Escudero":
                hab1 = new Habilidad("Atacar", 9, "");
                hab2 = new Habilidad("Empujar", 5, "Bloqueado");
                hab3 = new Habilidad("Fortalecer", 0, "Fortalecido");
                hab4 = new Habilidad("Bloquear", 0 , "Bloquead@");
                break;
            case "Mago":
                hab1 = new Habilidad("Bola de fuego", 7, "Quemadura");
                hab2 = new Habilidad("Lanzallamas", 11, "Quemadura");
                hab3 = new Habilidad("Dragon", 13, "Quemadura");
                hab4 = new Habilidad("Muro de llamas", 0, "Bloquead@");
                break;
            case "Guerrero":
                hab1 = new Habilidad("Ataque", 8, "");
                hab2 = new Habilidad("Ataque pesado", 11, "");
                hab3 = new Habilidad("Corte profundo", 13, "Sangrando");
                hab4 = new Habilidad("Gritar", 0, "Bloquead@");
                break;
        }
        this.habilidades.add(hab1);
        this.habilidadesDeClase.add(hab2);
        this.habilidadesDeClase.add(hab3);
        this.habilidadesDeClase.add(hab4);
    }

    public void recibirExp(int nivelEnemigo){
        int expRecibida = nivelEnemigo*2;
        int monedaConseguidas = (nivelEnemigo / 2) * 10;
        this.monedas += monedaConseguidas;
        System.out.printf("¡%s ha conseguido %s de experiencia!\n", this.nombre, expRecibida);
        if (this.experiencia + expRecibida >= expMAX){
            this.experiencia = (this.experiencia + expRecibida - expMAX);
            this.nivel+=1;
            this.expMAX += this.nivel * 10;
            this.nivelarPersonaje();
            System.out.printf("¡%s ha subido a nivel %s!\n", this.nombre, this.nivel);
        }
        else {
            this.experiencia += expRecibida;
        }
        System.out.printf("Experiencia: %s de %s\n", this.experiencia, this.expMAX);
        System.out.printf("%s ha conseguido %s monedas\n", this.nombre, monedaConseguidas);
        System.out.printf("Total de monedas %s\n", this.monedas);
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

    public int getMonedas(){
        return this.monedas;
    }

    public void agregarAlInventario(Objeto compra){
        this.inventario.add(compra);
    }

    public void curar(int cura){
        this.salud += cura;
    }

    public void RestarMonedas(int resta){
        this.monedas -= resta;
    }

    public int getSalud(){
        return this.salud;
    }

    public int getSaludMaxima(){
        return this.saludMaxima;
    }

    public boolean getEsEnemigo(){
        return this.esEnemigo;
    }

    public Personaje getRival(){
        return this.rival;
    }

    public void setRival(Personaje malulo){
        this.rival = malulo;
    }

    private void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
