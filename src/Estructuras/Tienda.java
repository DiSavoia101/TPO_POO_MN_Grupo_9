package Estructuras;

import Objetos.Armadura;
import Objetos.Objeto;
import Objetos.Pocion;
import Personajes.Personaje;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Tienda {
    protected String nombre;
    protected ArrayList<Objeto> objetos;
    protected Personaje cliente;
    Scanner lector = new Scanner(System.in);

    public Tienda() {
        this.nombre = "Cafetería UADE";
        this.objetos = new ArrayList<>();
        generarObjetos();
    }

    public void generarObjetos() {
        Objeto pocion15 = new Pocion(10,"UADE Cola","Cura 15 puntos de vida", 15);
        Objeto pocion30 = new Pocion(20,"Sanguche de milanga","Cura 30 puntos de vida", 30);
        Objeto armadura5 = new Armadura(35,"Buzo de la UADE",5, "Aumenta 5 puntos de defenza");
        Objeto armadura10 = new Armadura(50, "Lentes de Sol UADE", 10,"Aumenta 10 puntos de defenza");
        objetos.add(pocion15);
        objetos.add(pocion30);
        objetos.add(armadura5);
        objetos.add(armadura10);
    }

    public void mostrarObjetos(Personaje cliente){
        for (int i = 0; i < 20; i++){
            System.out.println("\n");
        }
        this.cliente = cliente;
        System.out.println("——————————————————————————————————————————————————————————");
        System.out.printf("¡Bienvenido a %s!\n", this.nombre);
        System.out.println("¡Escribe el número del objeto que quieras comprar!");
        System.out.println("——————————————————————————————————————————————————————————");
        System.out.printf("%-3s | %-20s | %-6s | %-20s\n", "N°", "Nombre", "Precio", "Información");
        System.out.println("——————————————————————————————————————————————————————————");
        for (int i = 0; i < objetos.size(); i++){
            System.out.printf("%-3s | %-20s | %-6s | %-20s\n", i+1, objetos.get(i).getNombre(), objetos.get(i).getValor(), objetos.get(i).getDescripcion());
        }
        System.out.printf("%-3s | Salir de la tienda\n", objetos.size()+1);
        System.out.println("——————————————————————————————————————————————————————————");
        System.out.println("Tus monedas: "+ cliente.getMonedas());
        System.out.println("¿Que objeto te gustaría comprar?");
        comprar();
        this.cliente = null;
    }

    public void comprar(){
        Objeto venta = null;
        boolean aux = false;
        String opcion = this.lector.nextLine();
        if (parseInt(opcion)-1 < objetos.size()){
            venta = objetos.get(parseInt(opcion)-1);
            if (cliente.getMonedas() >= venta.getValor()) {
                cliente.RestarMonedas(venta.getValor());
                cliente.agregarAlInventario(venta);
                if (parseInt(opcion)-1 >1 ){
                    this.objetos.remove(venta);
                }
                System.out.printf("¡Has adquirido %s por %s monedas exitosamente!", venta.getNombre(), venta.getValor());
            }
            else {
                System.out.println("No tienes monedas suficientes");
            }
            mostrarObjetos(cliente);
        }
        if (parseInt(opcion)-1 == objetos.size()){
            System.out.println("Hasta luego, vuelva pronto");
        }
    }
}
