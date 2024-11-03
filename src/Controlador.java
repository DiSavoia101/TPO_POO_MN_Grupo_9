import Mapa.Mundo;
import Personajes.Personaje;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador extends JFrame implements KeyListener {
    Mundo mapa;
    JFrame frame = new JFrame("Captura instantánea");

    public Controlador(){
        this.mapa = new Mundo(100,20);
        this.mapa.crearMundo();
    }
    public void iniciarJuego(){
        Personaje jugador = new Personaje("Carlos","Arquero", 60, 10, 55,5);
        this.mapa.genMalulazos();
        this.mapa.generarJugador(jugador);
        this.mapa.mostrarMundo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigoTecla = e.getKeyCode();
        switch (codigoTecla) {
            case KeyEvent.VK_W:
                mapa.moverPersonaje(0);
                break;
            case KeyEvent.VK_S:
                mapa.moverPersonaje(2);
                break;
            case KeyEvent.VK_A:
                mapa.moverPersonaje(1);
                break;
            case KeyEvent.VK_D:
                mapa.moverPersonaje(3);
                break;
        }
        mapa.mostrarMundo();
   }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
