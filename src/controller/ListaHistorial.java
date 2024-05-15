package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Historial;
import models.Json;
import models.Moneda;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaHistorial {
    private List<Historial> listaHistorial;
    private Historial historial;
    private Gson gson;
    private Json jsonHistorial;

    public ListaHistorial() {
        this.listaHistorial = new ArrayList<>();
        this.gson = new Gson();
        this.jsonHistorial = new Json("listaHistorial.json");

        obtenerHistorial();
    }

    /**
     * Metodo guardarHistorial,
     * guarda la lista de historial en una archivo json
     */
    public void guardarHistorial() {
        String json = this.gson.toJson(this.listaHistorial);
        this.jsonHistorial.escribirJson(json);
    }

    /**
     * Metodo obtenerHistorial,
     * obtiene el historial de converciones de monedas apartir de un json y se almacena e un {@code ArrayLista<Historial>}
     */
    public void obtenerHistorial() {
        Type tipoListaHitorial = new TypeToken<List<Historial>>() {
        }.getType();
//        if (this.jsonHistorial.leerJson(tipoListaHitorial) != null)
        this.listaHistorial = this.jsonHistorial.leerJson(tipoListaHitorial);
    }

    /**
     * Metodo agregar, agrega la convercion realizada
     *
     * @param monedaBase   contiene la {@code Moneda} base
     * @param valorBase    double valor de la moneda base
     * @param monedaCambio contiene la {@code Moneda} de cambio
     * @param valorCambio  double valor de la moneda de cambio
     */
    public void agregar(Moneda monedaBase, double valorBase, Moneda monedaCambio, double valorCambio) {
        this.historial = new Historial(fecha(), hora(), monedaBase.getMoneda(), monedaBase.getPais(), valorBase, monedaCambio.getMoneda(), monedaCambio.getPais(), valorCambio);
        this.listaHistorial.add(this.historial);
    }

    /**
     * Metodo getListaHistorial,
     *
     * @return {@code List<Historial>} lista de historiales del tipo {@code Historial}
     */
    public List<Historial> getListaHistorial() {
        return this.listaHistorial;
    }

    /**
     * Metodo getHistorial
     *
     * @return {@code Historial} mas reciente
     */
    public Historial getHistorial() {
        return this.historial;
    }

    /**
     * Metodo fecha
     *
     * @return {@code String} fecha atual con formato "yyyy/MM/dd"
     */
    public String fecha() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Metodo hora
     *
     * @return {@code String} hora actual con formato "HH:mm:ss"
     */
    public String hora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
