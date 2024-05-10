package controller;

import api.ExchangeRateApi;
import com.google.gson.Gson;
import records.CambioEr;

public class ConvercionMoneda {
    private ExchangeRateApi api;
    private ListaHistorial listaHistorial;
    private Gson gson;

    public ConvercionMoneda() {
        this.api = new ExchangeRateApi();
        this.listaHistorial = new ListaHistorial();
        this.gson = new Gson();

        listaHistorial.obtenerHistorial();
    }

    /**
     * Metodo tasarConverción, realiza una consulta a la API para obtener el valor del tipo de camvio de moneda
     *
     * @param monedaBase   Strign nombre de la moneda base
     * @param monedaCambio String nombre de la moneda a realizar la convercion
     * @param valorMoneda  bouble valor de la moneda base para calcular el canvio de moneda
     */
    public void tasarConvercion(String monedaBase, String monedaCambio, double valorMoneda) {
        String json = this.api.consultarCambio(monedaBase, monedaCambio);
        CambioEr cambioEr = gson.fromJson(json, CambioEr.class);
        double valorCambio = Double.parseDouble(cambioEr.conversion_rate());

        listaHistorial.agregar(monedaBase, valorMoneda, monedaCambio, valorCambio);

        guardarConvercion();
    }

    /**
     * Metodo guardarConvercion, guarda la converción realizada
     */
    private void guardarConvercion() {
        this.listaHistorial.guardarHistorial();
    }
}
