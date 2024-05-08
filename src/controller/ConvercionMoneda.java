package controller;

import api.Api;
import com.google.gson.Gson;
import records.CambioEr;

public class ConvercionMoneda {
    private Api api;
    private ListaHistorial listaHistorial;
    private Gson gson;

    public ConvercionMoneda() {
        this.api = new Api();
        this.listaHistorial = new ListaHistorial();
        this.gson = new Gson();

        listaHistorial.obtenerHistorial();
    }

    public void tasarConvercion(String monedaBase, String monedaCambio, double valorMoneda) {
        String json = this.api.consultarCambio(monedaBase, monedaCambio);
        CambioEr cambioEr = gson.fromJson(json, CambioEr.class);
        double valorCambio = Double.parseDouble(cambioEr.conversion_rate());

        listaHistorial.agregar(monedaBase, valorMoneda,monedaCambio, valorCambio);

        guardarConvercion();
    }

    private void guardarConvercion() {
        this.listaHistorial.guardarHistorial();
    }
}
