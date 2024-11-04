package Estructuras;

import Objetos.Armadura;
import Objetos.Objeto;
import Objetos.Pocion;
import Personajes.Personaje;

import java.util.HashSet;
import java.util.Scanner;


public class Tienda {
    protected String nombre;
    protected HashSet<Objeto> objetos;
    protected Personaje cliente;
    Scanner lector = new Scanner(System.in);

    public Tienda() {
        this.nombre = "Tienda DonJuan";
        this.objetos = new HashSet<>();
        generarObjetos();
    }

    public void generarObjetos() {
        Objeto pocion15 = new Pocion(10,"Pocion pequeña",15);
        Objeto pocion30 = new Pocion(20,"Pocion mediana",30);
        Objeto armadura = new Armadura(35,"Pechera de cobre",20);
        objetos.add(pocion15);
        objetos.add(pocion30);
        objetos.add(armadura);
    }

    public void mostrarObjetos(Personaje cliente){
        for (int i = 0; i < 20; i++){
            System.out.println("\n");
        }
        this.cliente = cliente;
        System.out.println(this.nombre);
        System.out.println("Escribe el nombre del objeto para comprar(ingresa adios para salir) ");
        System.out.println("Precio | Objetos");
        for (Objeto objeto : objetos){
            System.out.println(objeto.mostrarEnTienda());
        }
        System.out.println("Tus monedas: "+ cliente.getMonedas());
        comprar();
        this.cliente = null;
    }

    public void comprar(){

        Objeto venta = null;
        boolean aux = false;
        String opcion = this.lector.nextLine();
        while(!opcion.equals("adios")){
            for (Objeto objeto : objetos) {
                if (objeto.getNombre().equals(opcion)) {
                    aux = true;
                    if (cliente.getMonedas() >= objeto.getValor()) {

                        venta = objeto;
                        cliente.RestarMonedas(objeto.getValor());
                        cliente.agregarAlInventario(venta);
                        System.out.println("Algo mas?");
                    } else {
                        System.out.println("No tenes monedas suficientes");
                    }
                    break;
                }
            }
            if (!aux){
                System.out.println("No tengo ese objeto");
            }
            opcion = this.lector.nextLine();
        }
    }

}
