import controller.ConvercionMoneda;

import views.Menu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menuMonedaActual = new Menu();
        Menu menuMonedaCambio;
        ConvercionMoneda convercion = new ConvercionMoneda();
        int finalizar = 2;


        do {
            //Primer menú
            if (finalizar != 0) menuMonedaActual = new Menu(menuDeOpciones("Seleccione su moneda"));

            //Información de la moneda base seleccionado
            System.out.println("\nMoneda seleccionado: " + menuMonedaActual.getMonedaSeleccionado().getMoneda() +
                    " -> " + menuMonedaActual.getMonedaSeleccionado().getPais());
            //valor de la moneda base
            System.out.print("valor de Moneda: ");
            double valorDeMoneda = sc.nextDouble();
            System.out.println();

            //Segundo menú
            menuMonedaCambio = new Menu(menuDeOpciones("Seleccionar Moneda para Tasar el Cambio"));

            //Convercion de moneda
            convercion.tasarConvercion(menuMonedaActual.getMonedaSeleccionado().getMoneda(), menuMonedaCambio.getMonedaSeleccionado().getMoneda(), valorDeMoneda);

            finalizar = Menu.mostrarMenu();
        } while (finalizar != 2);
    }

    static Menu menuDeOpciones(String mensaje) {
        Menu menu = new Menu();

        do {
            menu.mostrarMenu(mensaje);
        } while (menu.opcion());

        return menu;
    }
}
