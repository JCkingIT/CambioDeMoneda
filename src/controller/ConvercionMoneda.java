package controller;

import api.ExchangeRateApi;
import com.google.gson.Gson;
import models.Moneda;
import records.CambioEr;

public class ConvercionMoneda {
    private ExchangeRateApi api;
    private ListaHistorial listaHistorial;
    private Gson gson;
    private Moneda monedaActual;
    private double valorMoneda;

    public ConvercionMoneda() {
        this.api = new ExchangeRateApi();
        this.listaHistorial = new ListaHistorial();
        this.gson = new Gson();

        listaHistorial.obtenerHistorial();
    }

    /**
     * Metodo tasarConverción, realiza una consulta a la API para obtener el valor del tipo de camvio de moneda
     *
     * @param monedaCambio {@code Moneda} a realizar la convercion
     */
    public void tasarConvercion(Moneda monedaCambio) {
        String json = this.api.consultarCambio(this.monedaActual.getMoneda(), monedaCambio.getMoneda());
        CambioEr cambioEr = gson.fromJson(json, CambioEr.class);
        double valorCambio = Double.parseDouble(cambioEr.conversion_rate());

        listaHistorial.agregar(this.monedaActual, this.valorMoneda, monedaCambio, valorCambio);

        guardarConvercion();
    }

    /**
     * Metodo setMonedaActual
     *
     * @param monedaBase  {@code Moneda} actual
     * @param valorMoneda double valor de la moneda actual
     */
    public void setMonedaActual(Moneda monedaBase, double valorMoneda) {
        this.monedaActual = new Moneda(monedaBase);
        this.valorMoneda = valorMoneda;
    }

    /**
     * Metodo getMonedaActual
     * @return {@code Moneda} actual
     */
    public Moneda getMonedaActual() {
        return this.monedaActual;
    }

    /**
     * Metodo getMonedaActual
     * @return Información de moneda actual
     */
    public void verMonedaActual() {
        System.out.println("Moneda seleccionado: "+monedaActual.getMoneda()+" -> "+monedaActual.getPais());
    }

    /**
     * Metodo guardarConvercion, guarda la converción realizada
     */
    private void guardarConvercion() {
        this.listaHistorial.guardarHistorial();
    }

    /**
     * Metodo verHistorialActual, muestra la comverción mas resiente.
     */
    public void verHistorialActual() {
        System.out.println();
        System.out.println("******************************************************************************************************************************************************");
        System.out.printf("%-30s%-30s%n", "Fecha: " + this.listaHistorial.getHistorial().getFecha(), "Hora: " + this.listaHistorial.getHistorial().getHora());
        System.out.printf("%-30s%-30s%n", "Moneda base: ", "Moneda de Cambio: ");
        System.out.printf("%-30s%-30s%n", this.listaHistorial.getHistorial().getMonedaBase() +" -> "+ this.listaHistorial.getHistorial().getMonedaBasePais(), this.listaHistorial.getHistorial().getMonedaCambio()  +" -> "+ this.listaHistorial.getHistorial().getMonedaCambioPais());
        System.out.printf("%-30s%-30s%-5s%-5s%n", "Valor de Moneda: " + this.listaHistorial.getHistorial().getValorBase(),
                "Valor de Moneda: " + this.listaHistorial.getHistorial().getValorCambio(),
                "->",
                "Converción de Moneda: " + this.listaHistorial.getHistorial().getValorFinal() + " " + this.listaHistorial.getHistorial().getMonedaCambio());
        System.out.println("******************************************************************************************************************************************************");
    }
}
