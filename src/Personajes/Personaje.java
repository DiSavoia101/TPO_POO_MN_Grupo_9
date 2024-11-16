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
    protected int nivel;
    protected int expMAX;
    protected int esquiva;
    protected int pntosDefenza;
    protected float potenciador = 1;
    protected Personaje rival;
    protected ArrayList<Habilidad> habilidades;
    protected ArrayList<Habilidad> habilidadesDeClase;
    protected ArrayList<Objeto> inventario;
    protected boolean esEnemigo;
    protected int monedas;
    Scanner lector = new Scanner(System.in);
    Random randomNumbers = new Random();

    public Personaje(String nombre, String clase, int saludPj, int pntosDefenzaPj, int esquivaPj, int nivelPj){
        this.nombre = nombre;
        this.clase = clase;
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.pntosDefenza = pntosDefenzaPj;
        this.habilidades = new ArrayList<Habilidad>();
        this.habilidadesDeClase = new ArrayList<Habilidad>();
        this.inventario = new ArrayList<Objeto>();
        this.nivel = nivelPj;
        this.monedas = 20;
        this.esEnemigo = false;
        genHabilidades();
        nivelarPersonaje();
    }

    public Personaje(String nombre, int saludPj, int pntosDefenzaPj, int esquivaPj, int nivelPj){
        this.nombre = nombre;
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.esEnemigo = true;
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
        Habilidad hab2 = new Habilidad("Poner 3.59", 400000000, "Depresion");
        Habilidad hab3 = new Habilidad("Sancionado", 100, "");
        Habilidad hab4 = new Habilidad("Trompada", 49, "");

        this.habilidades.add(hab1);
        this.habilidades.add(hab2);
        this.habilidades.add(hab3);
        this.habilidades.add(hab4);
    }

    public void recibirDanio(Habilidad hab){
        //logica de tu defensa
        if (hab != null){
            int danio = hab.getDanio();
            danio = (int) (danio*this.potenciador);
            if (danio > this.pntosDefenza) { // 5 def, 10 daño. salud - 5
                salud -= danio - this.pntosDefenza;
                System.out.printf("%s recibió %s de daño\n", this.getNombre(), (danio - this.pntosDefenza));
            }

            if (hab.efecto.equals("Envenenad@") || hab.efecto.equals("En Llamas") || hab.efecto.equals("Sangrando") || this.duracionEfecto > 0){
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
        if (this.isAlive() && this.rival.isAlive()){
            if (this.esEnemigo && !( Objects.requireNonNull(hab).efecto.equals("Bloquead@")|| hab.efecto.equals("Evasion"))) {
                habRandom();
            } else {
                if (this.esEnemigo){
                    System.out.printf("El ataque de %s se vio frustrado por el estado %s", this.nombre, hab.efecto);
                }
                menuPelea();
            }
        }
        else {
            if (!this.isAlive()){
                System.out.printf("%s ha muerto, el combate ha terminado\n", this.getNombre());
            }
        }
    }

    public void habRandom(){

        int ataque = randomNumbers.nextInt(this.habilidades.size());
        System.out.printf("El %s utiliza %s\n", this.nombre, habilidades.get(ataque).getNombre());
        this.rival.recibirDanio(this.habilidades.get(ataque));

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
        if (this.nivel == 2){
            this.habilidades.add(habilidadesDeClase.getFirst());
            System.out.printf("¡%s ha conseguido la habilidad '%s'!", this.nombre, this.habilidadesDeClase.getFirst().getNombre());
        }
        if (this.nivel == 4){
            this.habilidades.add(habilidadesDeClase.get(1));
            System.out.printf("¡%s ha conseguido la habilidad '%s'!", this.nombre, this.habilidadesDeClase.get(1).getNombre());
        }
        if (this.nivel == 8){
            this.habilidades.add(habilidadesDeClase.get(2));
            System.out.printf("¡%s ha conseguido la habilidad '%s'!", this.nombre, this.habilidadesDeClase.get(2).getNombre());
        }
        this.saludMaxima = salud + (this.nivel*2);
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
        if (Objects.equals(this.clase, "Arquero")) {
            hab1 = new Habilidad("Flechazo", 8, "");
            hab2 = new Habilidad("Flecha Veneno", 6, "Envenenad@");
            hab3 = new Habilidad("Lluvia de flechas", 12, "");
            hab4 = new Habilidad("Evasion", 0, "Evasion");
        } else if (Objects.equals(this.clase, "Escudero")) {
            hab1 = new Habilidad("Atacar", 9, "");
            hab2 = new Habilidad("Empujar", 5, "Aturdido");
            hab3 = new Habilidad("Fortalecer", 0, "Fortalecido");
            hab4 = new Habilidad("Bloquear", 0 , "Bloqueado");
        } else if (Objects.equals(this.clase, "Mago")) {
            hab1 = new Habilidad("Bola de fuego", 7, "En llamas");
            hab2 = new Habilidad("Lanzallamas", 11, "En llamas");
            hab3 = new Habilidad("Dragon", 13, "En llamas");
            hab4 = new Habilidad("Muro de llamas", 0, "Bloqueado");
        } else if (Objects.equals(this.clase, "Guerrero")) {
            hab1 = new Habilidad("Ataque", 8, "");
            hab2 = new Habilidad("Ataque pesado", 11, "");
            hab3 = new Habilidad("Corte profundo", 13, "Sangrando");
            hab4 = new Habilidad("Gritar", 0, "Debilidad");
        }
        this.habilidades.add(hab1);
        this.habilidadesDeClase.add(hab2);
        this.habilidadesDeClase.add(hab3);
        this.habilidadesDeClase.add(hab4);
    }

    public void recibirExp(int nivelEnemigo){
        int expRecibida = nivelEnemigo*2;
        int monedaDelMuerto = (nivelEnemigo / 2) * 10;
        this.monedas += monedaDelMuerto;
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
        System.out.printf("Encontraste %s monedas en el piso \n", monedaDelMuerto);
        System.out.printf("Total de monedas %s", this.monedas);
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

}
