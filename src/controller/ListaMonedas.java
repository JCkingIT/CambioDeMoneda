package controller;

import api.ExchangeRateApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Json;
import models.Moneda;
import records.MonedaEr;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListaMonedas {
    List<Moneda> listaMonedas;
    ExchangeRateApi api = new ExchangeRateApi();
    Gson gson = new Gson();
    Json jsonMoendas;
    private int posicionDeLista = 0;
    private final int grupoLista = 8;

    /**
     * Constructor ListaMonedas
     * obtiene la lista de monedas de un json, si el json no existe consulta a la api y genera un json con la lista de monedas
     */
    public ListaMonedas() {
        this.listaMonedas = new ArrayList<>();
        this.jsonMoendas = new Json("listaMonedas.json");

        if (obtenerListaMonedas()) {
            consultarApi();
        }
    }

    /**
     * Metodo obtenerListaMonedas
     *
     * @return {@code true} si no contiene lista de monedas, {@code false} si contiene lista de monedas
     */
    private boolean obtenerListaMonedas() {
        Type tipoListaMoneda = new TypeToken<List<Moneda>>() {
        }.getType();
//        if (this.jsonMoendas.leerJson(tipoListaMoneda) != null)
        this.listaMonedas = this.jsonMoendas.leerJson(tipoListaMoneda);

        return this.listaMonedas.isEmpty();
    }

    /**
     * Metodo consultarApi,
     * obtiene la lista de monedas de la api
     */
    private void consultarApi() {
        String json = this.api.consultarMonedas();
        MonedaEr monedaLista = this.gson.fromJson(json, MonedaEr.class);

        for (String[] item : monedaLista.supported_codes()) {
            Moneda moneda = new Moneda(item[0], item[1]);
            this.listaMonedas.add(moneda);
        }

        guardarListaMonedas();
    }

    /**
     * Metodo guardarListaMonedas,
     * guarda las listas de monedas en un archivo json
     */
    private void guardarListaMonedas() {
        this.jsonMoendas.escribirJson(gson.toJson(listaMonedas));
    }

    /**
     * Metodo mostrarGrupoMonedas
     *
     * @return {@code List<Moneda>} lista que contiene 8 elemetos de tipo {@code Moneda}
     */
    public List<Moneda> mostrarGrupoMonedas() {
        List<Moneda> lista = new ArrayList<>();
        for (int i = this.posicionDeLista; i < (this.posicionDeLista + this.grupoLista); i++) {
            try {
                lista.add(this.listaMonedas.get(i));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return lista;
    }

    /**
     * Metodo siguienteListaMonedas,
     * se desplaza 8 posiciones de la lista principal
     *
     * @return {@code 1} int -> grupo de listas que se encuentran al centro de la lista principal,
     * {@code 2} int -> grupo de listas que se encuentran al final de la lista principal
     */
    public int siguienteListaMonedas() {
        this.posicionDeLista += (this.posicionDeLista < this.listaMonedas.size()) ? this.grupoLista : 0;
        return ((this.posicionDeLista + 8) < this.listaMonedas.size()) ? 1 : 2;
    }

    /**
     * Metodo retrocederListaMonedas,
     * retrocede 8 posiciones de la lista principal
     *
     * @return {@code 0} int -> grupo de listas que se encuentran al inicio de la lista principal,
     * {@code 1} int -> grupo de listas que se encuentran al centro de la lista principal
     */
    public int retrocederListaMonedas() {
        this.posicionDeLista -= (this.posicionDeLista > 0) ? this.grupoLista : 0;
        return (this.posicionDeLista > 0) ? 1 : 0;
    }

    /**
     * Metodo getPosicionDeLista
     * @return {@code int} posición donde se encuentra.
     */
    public int paginaDeGrupoMonedas() {
        return (this.posicionDeLista / this.grupoLista) + 1;
    }
}
