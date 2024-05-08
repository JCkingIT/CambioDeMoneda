package controller;

import api.Api;
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
    Api api = new Api();
    Gson gson = new Gson();
    Json jsonMoendas;
    Moneda moneda;
    private int posicionDeLista = 0;
    private final int grupoLista = 8;

    public ListaMonedas() {
        this.listaMonedas = new ArrayList<>();
        this.jsonMoendas = new Json("listaMonedas.json");

        if (obtenerListaMonedas()) {
            consultarApi();
        }
    }

    private boolean obtenerListaMonedas() {
        Type tipoListaMoneda = new TypeToken<List<Moneda>>() {
        }.getType();
//        if (this.jsonMoendas.leerJson(tipoListaMoneda) != null)
            this.listaMonedas = this.jsonMoendas.leerJson(tipoListaMoneda);

        return this.listaMonedas.isEmpty();
    }

    private void consultarApi() {
        String json = this.api.consultarMonedas();
        MonedaEr monedaLista = this.gson.fromJson(json, MonedaEr.class);

        for (String[] item : monedaLista.supported_codes()) {
            this.moneda = new Moneda(item[0], item[1]);
            this.listaMonedas.add(this.moneda);
        }

        guardarListaMonedas();
    }

    private void guardarListaMonedas() {
        this.jsonMoendas.escribirJson(gson.toJson(listaMonedas));
    }

    public ArrayList<Moneda> mostrarGrupoMonedas() {
        ArrayList<Moneda> lista = new ArrayList<>();
        for (int i = this.posicionDeLista; i < (this.posicionDeLista + this.grupoLista); i++)
            lista.add(this.listaMonedas.get(i));
        return lista;
    }

    public int siguienteListaMonedas() {
        this.posicionDeLista += (this.posicionDeLista < this.listaMonedas.size()) ? this.grupoLista : 0;
        return (this.posicionDeLista < this.listaMonedas.size()) ? 1 : 2;
    }

    public int retrocederListaMonedas() {
        this.posicionDeLista -= (this.posicionDeLista > 0) ? this.grupoLista : 0;
        return (this.posicionDeLista > 0) ? 1 : 0;
    }
}
