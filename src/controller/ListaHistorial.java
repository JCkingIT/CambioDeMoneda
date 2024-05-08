package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Historial;
import models.Json;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaHistorial {
    private List<Historial> listaHistorial;
    private Gson gson;
    private Json jsonHistorial;

    public ListaHistorial() {
        this.listaHistorial = new ArrayList<>();
        this.gson = new Gson();
        this.jsonHistorial = new Json("listaHistorial.json");

        obtenerHistorial();
    }

    public void guardarHistorial() {
        String json = this.gson.toJson(this.listaHistorial);
        this.jsonHistorial.escribirJson(json);
    }

    public void obtenerHistorial() {
        Type tipoListaHitorial = new TypeToken<List<Historial>>() {
        }.getType();
//        if (this.jsonHistorial.leerJson(tipoListaHitorial) != null)
            this.listaHistorial = this.jsonHistorial.leerJson(tipoListaHitorial);
    }

    public void agregar(String monedaBase, double valorBase, String monedaCambio, double valorCambio) {
        Historial historial = new Historial(fecha(), hora(), monedaBase, valorBase, monedaCambio, valorCambio);
        this.listaHistorial.add(historial);
    }

    public List<Historial> getListaHistorial() {
        return this.listaHistorial;
    }

    public String fecha() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String hora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
