package views;

import controller.ListaHistorial;
import controller.ListaMonedas;
import models.Historial;
import models.Moneda;
import utilities.Numero;

import java.io.*;
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

    public static int mostrarMenu() {
        System.out.println();
        marco();
        System.out.printf("%-30S%-30S %n", "", "Selecciona una de las opciones para continuar");
        marco();
        System.out.printf("%-40s%-40s%-40s%n", "0 -> Realizar otro Cambio", "1 -> Cambiar moneda base", "2 -> Finalizar");
        marco();
        return scanner(3);
    }

    public void mostrarMenu(String titulo) {
        this.grupoMonedas = listaMonedas.mostrarGrupoMonedas();

        marco('*');
        System.out.printf("%60S %n", titulo);
        marco('*');
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-60s %-60s %n", (i + 1) + "  : " + this.grupoMonedas.get(i).getMoneda() + " -> " + this.grupoMonedas.get(i).getPais(),
                    (i + 5) + "  : " + this.grupoMonedas.get(i + 4).getMoneda() + " -> " + this.grupoMonedas.get(i + 4).getPais());
        }
        marco('-');
        switch (this.posicionMenu) {
            case 0:
                System.out.printf("%-60s %-60s %n", "9  : Mostrar más", "0  : Mostrar Historial");
                System.out.println("00 : Salir");
                break;
            case 1:
                System.out.printf("%-60s %-60s %n", "9  : Mostrar más", "99 : Mostrar anterior");
                System.out.printf("%-60s %-60s %n", "0  : Mostrar Historial", "00 : Salir");
                break;
            case 2:
                System.out.printf("%-40s %-40s %n", "99 : Mostrar Anterior", "0  : Mostrar Historial");
                System.out.println("00 : Salir");
                break;

        }
        marco('*');
    }

    private static void marco() {
        for (int i = 0; i < 110; i++) {
            System.out.print('*');
        }
        System.out.println();
    }

    private void marco(char signo) {
        for (int i = 0; i < 110; i++) {
            System.out.print(signo);
        }
        System.out.println();
    }

    public boolean opcion() {
        boolean resultado = false;
        String seleccion = scanner();

        switch (seleccion) {
            case "9":
                if (this.posicionMenu == 2) {
                    System.out.println("Opcion no disponible");
                    resultado = opcion();
                    break;
                }
                System.out.println("\n");
                masOpciones();
                resultado = true;
                break;
            case "99":
                if (this.posicionMenu == 0) {
                    System.out.println("¡¡¡¡Opcion no disponible!!!!");
                    resultado = opcion();
                    break;
                }
                System.out.println("\n");
                anteriorOpciones();
                resultado = true;
                break;

            case "0":
                mostrarHistorial();
                System.exit(0);
                break;
            case "00":
                System.exit(0);
                break;
            default:
                this.posicionLista = Integer.parseInt(seleccion) - 1;
                if (!(this.posicionLista > 8)) {
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

        do {
            System.out.print(" Ingresa el numero de su opción: ");
            opcion = sc.nextInt();
            if (!Numero.isInteger(String.valueOf(opcion))) System.out.println("Ingresa un numero");
            if (opcion > limite - 1) System.out.println("Opción no disponible");
        } while (!Numero.isInteger(String.valueOf(opcion)) && (opcion > limite - 1));

        return opcion;
    }

    private String scanner() {
        Scanner sc = new Scanner(System.in);
        String opcion;

        do {
            System.out.print(" Ingresa el numero de su opción: ");
            opcion = sc.next();
            if (!Numero.isInteger(opcion)) System.out.println("Ingresa un numero");
        } while (!Numero.isInteger(opcion));

        return opcion;
    }

    private void masOpciones() {
        this.posicionMenu = listaMonedas.siguienteListaMonedas();
    }

    private void anteriorOpciones() {
        this.posicionMenu = listaMonedas.retrocederListaMonedas();
    }

    private void mostrarHistorial() {
        ListaHistorial listaHistorial = new ListaHistorial();
        System.out.println();
        marco('*');
        System.out.printf("%60S %n", "Historial de Comverción de moneda");
        for (Historial historial : listaHistorial.getListaHistorial()) {
            marco('-');
            System.out.printf("%-30s%-30s%n", "Fecha: " + historial.getFecha(), "Hora: " + historial.getHora());
            System.out.printf("%-30s%-30s%n", "Moneda base: " + historial.getMonedaBase(), "Moneda de Cambio: " + historial.getMonedaCambio());
            System.out.printf("%-30s%-30s%-5s%-5s%n", "Valor de Moneda: " + historial.getValorBase(),
                    "Valor de Moneda: " + historial.getValorCambio(),
                    "->",
                    "Converción de Moneda: " + historial.getValorFinal() + " " + historial.getMonedaCambio());
//            marco('-');
        }
        marco('*');
    }
}
