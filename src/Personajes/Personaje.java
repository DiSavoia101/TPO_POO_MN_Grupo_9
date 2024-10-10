package Personajes;

public class Personaje {
    private int salud;
    private int experiencia;
    private int dinero;
    private int nivel;
    private int xpMAX;
    private int esquiva;
    private int pntosAtaque;
    private int pntosDefenza;

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
    public int atacar(){
        return pntosAtaque;
    }

    public int recibirDanio(){
        return -1;
    }

    public void subirXP(int expGanada){
        return;
    }


}
