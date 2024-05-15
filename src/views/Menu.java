package views;

import controller.ListaHistorial;
import controller.ListaMonedas;
import models.Historial;
import models.Moneda;
import utilities.Numero;

import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private File listaMonedasJson;
    private ListaMonedas listaMonedas;
    private List<Moneda> grupoMonedas;
    private Moneda monedaSeleccionado;

    private int posicionMenu = 0; //0 inicio | 1 centro | 2 final -> para mostra mensaje adicional.
    private int posicionLista;

    public Menu() {
        this.listaMonedas = new ListaMonedas();
    }

    public Menu(Menu menu) {
        this.listaMonedasJson = menu.listaMonedasJson;
        this.listaMonedas = menu.listaMonedas;
        this.grupoMonedas = menu.grupoMonedas;
        this.monedaSeleccionado = menu.monedaSeleccionado;
        this.posicionMenu = menu.posicionMenu;
        this.posicionLista = menu.posicionLista;
    }

    public Moneda getMonedaSeleccionado() {
        return this.monedaSeleccionado;
    }

    public void informacionDeMonedaSeleccionado() {
        System.out.println("\nMoneda seleccionado: " + this.monedaSeleccionado.getMoneda() +
                " -> " + this.monedaSeleccionado.getPais());
    }

    public static int menuPrincipal(int numeroOpcion) {
        System.out.println();
        marco();
        System.out.printf("%-30S%-50S %n", "", "Menu principal");
        marco();
        System.out.printf("%-40s%-40s%-40s%n", "1 -> Tasar Converción de Moneda", "2 -> Historial", "0 -> Salir");
        marco();
        return scanner(numeroOpcion);
    }

    public static int menuSecundario(int numeroOpcion) {
        System.out.println();
        marco();
        System.out.printf("%-40s%-40s%-40s%-40s%n", "1 -> Cambiar de moneda principal", "2 -> Realizar otra convercion","3 -> Ir al menu principal", "0 -> Salir");
        marco();
        return scanner(numeroOpcion);
    }

    public void mostrarMenuDeMoneda(String titulo) {
        this.grupoMonedas = listaMonedas.mostrarGrupoMonedas();

        System.out.println();
        marco('*');
        System.out.printf("%50S %40S %n", titulo,"Pg: "+listaMonedas.paginaDeGrupoMonedas());
        marco('*');
        for (int i = 0; i < 8; i+=2) {
                try{
                    System.out.printf("%-60s", (i + 1) + "  : " + this.grupoMonedas.get(i).getMoneda() + " -> " + this.grupoMonedas.get(i).getPais());
                    System.out.printf("%-60s %n",(i + 2) + "  : " + this.grupoMonedas.get(i + 1).getMoneda() + " -> " + this.grupoMonedas.get(i + 1).getPais());
                }catch (IndexOutOfBoundsException e){
                    break;
                }
        }
        marco('-');
        switch (this.posicionMenu) {
            case 0:
                System.out.printf("%-60s %-60s %n", "9  : Mostrar más", "0  : Salir");
                break;
            case 1:
                System.out.printf("%-60s %-60s %n", "9  : Mostrar más", "99 : Mostrar anterior");
                System.out.printf("%-60s %n", "0 : Salir");
                break;
            case 2:
                System.out.printf("%-40s %-40s %n", "99 : Mostrar Anterior", "0  : Salir");
                break;
        }
        marco('*');
    }

    private static void marco() {
        for (int i = 0; i < 150; i++) {
            System.out.print('*');
        }
        System.out.println();
    }

    private void marco(char signo) {
        for (int i = 0; i < 150; i++) {
            System.out.print(signo);
        }
        System.out.println();
    }

    public boolean opcion() {
        boolean resultado = false;
        int seleccion = scanner();

        switch (seleccion) {
            case 9:
                if (this.posicionMenu == 2) {
                    System.out.println("Opcion no disponible");
                    resultado = opcion();
                    break;
                }
                System.out.println("\n");
                masOpciones();
                resultado = true;
                break;
            case 99:
                if (this.posicionMenu == 0) {
                    System.out.println("¡¡¡¡Opcion no disponible!!!!");
                    resultado = opcion();
                    break;
                }
                System.out.println("\n");
                anteriorOpciones();
                resultado = true;
                break;
            case 0:
                System.exit(0);
                break;
            default:
                this.posicionLista = seleccion - 1;
                if (!(this.posicionLista > this.grupoMonedas.size()) && this.posicionLista >= 0) {
                    monedaSeleccionado = new Moneda(grupoMonedas.get(posicionLista));
                    break;
                }
                System.out.println("¡¡¡¡Opcion no disponible!!!!");
                resultado = opcion();
        }
        return resultado;
    }

    private static int scanner(int limite) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            System.out.print(" Ingresa el numero de su opción: ");
            opcion = sc.nextInt();
            if (opcion > limite - 1) System.out.println("!!!!Opcion no disponible!!!!");
            return (opcion > limite - 1) ? scanner(limite) : opcion;
        } catch (InputMismatchException e) {
            System.out.println("!!!!Ingresa un número!!!!");
            return scanner(limite);
        }
    }

    private int scanner() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print(" Ingresa el numero de su opción: ");
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("!!!!Ingresa un número!!!!");
            return scanner();
        }
    }

    static public double valorDeMoneda() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Ingresa el valor de la moneda: ");
            return sc.nextDouble();
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un número valido");
            return valorDeMoneda();
        }
    }

    private void masOpciones() {
        this.posicionMenu = listaMonedas.siguienteListaMonedas();
    }

    private void anteriorOpciones() {
        this.posicionMenu = listaMonedas.retrocederListaMonedas();
    }

    public void mostrarHistorial() {
        ListaHistorial listaHistorial = new ListaHistorial();
        System.out.println();
        marco('*');
        System.out.printf("%60S %n", "Historial de Comverción de moneda");
        if (listaHistorial.getListaHistorial().isEmpty()) {
            marco('-');
            System.out.println("Historial vasio, realiza una converción para visualizar en el historial");
        } else {
            for (Historial historial : listaHistorial.getListaHistorial()) {
                marco('-');
                System.out.printf("%-50s%-50s%n", "Fecha: " + historial.getFecha(), "Hora: " + historial.getHora());
                System.out.printf("%-50s%-50s%n", "Moneda base: ", "Moneda de Cambio: ");
                System.out.printf("%-50s%-50s%n",
                        historial.getMonedaBase() + " : " + historial.getMonedaBasePais(),
                        historial.getMonedaCambio() + " : " + historial.getMonedaCambioPais());
                System.out.printf("%-50s%-50s%-5s%-5s%n", "Valor de Moneda: " + historial.getValorBase(),
                        "Valor de Moneda: " + historial.getValorCambio(),
                        "->",
                        "Converción de Moneda: " + historial.getValorFinal() + " " + historial.getMonedaCambio());
            }
        }
        marco('*');
        System.out.printf("%-60s%-60s%n", "1 -> Ir a menu principal", "0 -> Salir ");
        marco('*');
        if (scanner(2) == 0) System.exit(0);
    }
}
