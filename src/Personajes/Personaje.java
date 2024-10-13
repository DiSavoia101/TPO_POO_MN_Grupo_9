package Personajes;

public class Personaje {
    protected int salud;
    protected int experiencia;
    protected int dinero;
    protected int nivel;
    protected int xpMAX;
    protected int esquiva;
    protected int pntosAtaque;
    protected int pntosDefenza;

    public Personaje(int saludPj, int pntosAtaquePj, int pntosDefenzaPj, int esquivaPj){
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.pntosAtaque = pntosAtaquePj;
        this.pntosDefenza = pntosDefenzaPj;
    }

    public Personaje(int saludPj, int pntosAtaquePj, int pntosDefenzaPj, int esquivaPj, int nivelPj){
        this.salud = saludPj;
        this.esquiva = esquivaPj;
        this.pntosAtaque = pntosAtaquePj;
        this.pntosDefenza = pntosDefenzaPj;
        this.nivel = nivelPj;
    }
    public Personaje(){};

    public int atacar(){
        return this.pntosAtaque;
    }

    public int recibirDanio(){
        return -1;
    }

    public void subirXP(int expGanada){
        return;
    }

    public int mostrarVida(){
        return this.salud;
    }
}
