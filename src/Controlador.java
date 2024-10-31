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
        Personaje jugador = new Personaje("Arquero", 60, 10, 55,5);
        this.mapa.generarJugador(jugador);
        Personaje bandido = new Personaje("Bandido", 30,10,20,3);
        this.mapa.genMalulazos(bandido);

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


    public void mover(){
        JTextField textField = new JTextField(20);

//        boolean es = false;
//        int dir = 0;
//        do {
//            String ingreso = input.next();
//            for (int i = 0; i < 4; i++) {
//                if (moveKeys.get(i).equals(ingreso)) {
//                    es = true;
//                    dir = i;
//                    break;
//                }
//            }
//        } while (!es);
//        mapa.moverPersonaje(dir);
//        System.out.flush();
//        mapa.mostrarMundo();
//        mover();
    }
}