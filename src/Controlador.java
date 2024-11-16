import Mapa.Mundo;
import Personajes.Personaje;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Controlador extends JFrame implements KeyListener {
    Mundo mapa;
    JFrame frame = new JFrame("Captura instantánea");
    Scanner lector = new Scanner(System.in);

    public Controlador(){
        this.mapa = new Mundo(100,20);
        this.mapa.crearMundo();
    }
    public int elegirClase(){
        System.out.println("Ingresa tu clase:");
        System.out.println("————————————————————————");
        System.out.println("1 - Arquero"); // 1 → 0
        System.out.println("2 - Escudero"); // 2 → 1
        System.out.println("3 - Guerrero");
        System.out.println("4 - Mago");
        System.out.println("————————————————————————");

        try {
            String clase = this.lector.next();
            if (parseInt(clase) <= 0 || parseInt(clase) > 4){
                throw new NumeroEnRangoErroneo("Número Erroneo, volve a intentarlo");
            }
            return parseInt(clase) - 1;
        }
        catch (NumeroEnRangoErroneo error){
            System.out.println(error);
            elegirClase();
        }
        return 0;
    }

    public String menuInicio(){
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        String bem =" ######   ######    #####   ##   ##   #####     ####    ####     #####   ##   ##    ##     ######                     #######  ####                 ####  ##   ##  #######    ####    #####     ##\n" +
                "  ##  ##   ##  ##  ##   ##  ### ###  ##   ##   ##  ##    ##     ##   ##  ###  ##   ####     ##  ##                     ##   #   ##                   ##   ##   ##   ##   #   ##  ##  ##   ##   ####\n" +
                "  ##  ##   ##  ##  ##   ##  #######  ##   ##  ##         ##     ##   ##  #### ##  ##  ##    ##  ##                     ## #     ##                   ##   ##   ##   ## #    ##       ##   ##   ####\n" +
                "  #####    #####   ##   ##  #######  ##   ##  ##         ##     ##   ##  ## ####  ##  ##    #####                      ####     ##                   ##   ##   ##   ####    ##       ##   ##    ##\n" +
                "  ##       ## ##   ##   ##  ## # ##  ##   ##  ##         ##     ##   ##  ##  ###  ######    ## ##                      ## #     ##   #           ##  ##   ##   ##   ## #    ##  ###  ##   ##    ##\n" +
                "  ##       ##  ##  ##   ##  ##   ##  ##   ##   ##  ##    ##     ##   ##  ##   ##  ##  ##    ##  ##    ##               ##   #   ##  ##           ##  ##   ##   ##   ##   #   ##  ##  ##   ##\n" +
                " ####     #### ##   #####   ##   ##   #####     ####    ####     #####   ##   ##  ##  ##   #### ##    ##              #######  #######            ####     #####   #######    #####   #####     ##\n" +
                "                                                                                                     ##\n";
        System.out.println(bem);
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        System.out.println("Ingresa el nombre de tu personaje:");
        System.out.println("———————————————————————————————————");
        String nombre = lector.nextLine();
        return nombre;
    }
    public String ingresarClase(){
        int numeroIngresado = elegirClase();
        ArrayList<String> clases = new ArrayList<String>();
        clases.add("Arquero");
        clases.add("Escudero");
        clases.add("Guerrero");
        clases.add("Mago");
        String clase = clases.get(numeroIngresado);
        return clase;
    }
    public void iniciarJuego(){
        String nombre = menuInicio();
        String clase = ingresarClase();
        Personaje jugador = new Personaje(nombre, clase, 60, 8, 55,1);
        this.mapa.generarJugador(jugador);
        this.mapa.genMalulazos(jugador.getNivel());
        this.mapa.genTiendaDePrueba();
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
