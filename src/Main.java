import controller.ConvercionMoneda;
import views.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        ConvercionMoneda convercion = new ConvercionMoneda();
        int finalizar = 0;


        do {
            finalizar = Menu.menuPrincipal(3);

            switch (finalizar) {
                case 0:
                    System.out.println("Saliendo,");
                    break;
                case 1:
                    do {
                        //Primer menú
                        if (finalizar == 1) menu = new Menu(menuDeOpciones("Seleccione su moneda"));

                        //Información de la moneda base seleccionado
                        if (finalizar == 2) convercion.verMonedaActual();
                        else menu.informacionDeMonedaSeleccionado();

                        convercion.setMonedaActual(((finalizar != 2)?menu.getMonedaSeleccionado():convercion.getMonedaActual()), Menu.valorDeMoneda());
                        //Segundo menú
                        menu = new Menu(menuDeOpciones("Seleccionar Moneda para Tasar el Cambio"));

                        //Convercion de moneda
                        convercion.tasarConvercion(menu.getMonedaSeleccionado());
                        convercion.verHistorialActual();
                        finalizar = Menu.menuSecundario(4);
                        if(finalizar == 3) break;
                    } while (finalizar != 0);
                    break;
                case 2:
                    menu.mostrarHistorial();
                    break;
            }
        } while (finalizar != 0);
    }

    static Menu menuDeOpciones(String mensaje) {
        Menu menu = new Menu();

        do {
            menu.mostrarMenuDeMoneda(mensaje);
        } while (menu.opcion());

        return menu;
    }
}
