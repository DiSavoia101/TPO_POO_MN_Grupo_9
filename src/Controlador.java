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
        String titulo =" ######   ######    #####   ##   ##   #####     ####    ####     #####   ##   ##    ##     ######                     #######  ####                 ####  ##   ##  #######    ####    #####     ##\n" +
                "  ##  ##   ##  ##  ##   ##  ### ###  ##   ##   ##  ##    ##     ##   ##  ###  ##   ####     ##  ##                     ##   #   ##                   ##   ##   ##   ##   #   ##  ##  ##   ##   ####\n" +
                "  ##  ##   ##  ##  ##   ##  #######  ##   ##  ##         ##     ##   ##  #### ##  ##  ##    ##  ##                     ## #     ##                   ##   ##   ##   ## #    ##       ##   ##   ####\n" +
                "  #####    #####   ##   ##  #######  ##   ##  ##         ##     ##   ##  ## ####  ##  ##    #####                      ####     ##                   ##   ##   ##   ####    ##       ##   ##    ##\n" +
                "  ##       ## ##   ##   ##  ## # ##  ##   ##  ##         ##     ##   ##  ##  ###  ######    ## ##                      ## #     ##   #           ##  ##   ##   ##   ## #    ##  ###  ##   ##    ##\n" +
                "  ##       ##  ##  ##   ##  ##   ##  ##   ##   ##  ##    ##     ##   ##  ##   ##  ##  ##    ##  ##    ##               ##   #   ##  ##           ##  ##   ##   ##   ##   #   ##  ##  ##   ##\n" +
                " ####     #### ##   #####   ##   ##   #####     ####    ####     #####   ##   ##  ##  ##   #### ##    ##              #######  #######            ####     #####   #######    #####   #####     ##\n" +
                "                                                                                                     ##\n";
        String lore = "Esta es la historia de una persona.\n"+
                "Una persona con un objetivo claro.\n"+
                "Promocionar Paradigma Orientado a Objetos.\n"+
                "Pero eso no iba a ser fácil, no, no.\n"+
                "Para lograrlo habría de enfrentarse a diversos peligros.\n"+
                "Combatir contra el enjambre de criaturas que osaban hacerse llamar 'compañeros'\n"+
                "Y enfrentarse al mayor duelo de todos.\n"+
                "No hay combate más grande al que pueda enfrentarse uno en la vida...\n"+
                "Que el combate contra un profesor para que te promocione una materia.\n"+
                "Pero esta persona no iba a rendirse, llegaría hasta el profesor y lo convencería.\n"+
                "Y si no lo hacía por las buenas...\n"+
                "Ḻ̸̄O̶͙̅ ̵̫͉̀H̷̻̊̌Ȁ̵̼͒R̷̭̂͗I̵͙͕͊A̶̩͎̍͑ ̴̣̚͝P̴̘̺͘Ò̴͙̒Ṛ̵̲̅ ̴̢̝̅͗L̸͔̺̉Ã̴̢S̸̻̲̅ ̸̨͛̋M̶̧̕͝A̸̤̽̈́L̸̫͍̈́͝Ā̸̖̫S̴̨̹͗̓\n"+
                "Esta es la historia de como esta persona promocionó.\n"+
                "O como falló en el intento.\n"+
                "Si promociona o muere, solo tu puedes decidirlo.\n";
        System.out.println(titulo);
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————\n");
        System.out.println(lore);
        System.out.println("———————————————————————————————————");
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
        Personaje jugador = new Personaje(nombre, clase, 60, 5,10);
        this.mapa.generarJugador(jugador);
        this.mapa.genMalulazos();
        this.mapa.genTienda();
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
        try {
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
        } catch (InterruptedException er) {
            Thread.currentThread().interrupt();
            System.err.println("Hubo un error: "+ er.getMessage());
        }
        mapa.mostrarMundo();
   }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
