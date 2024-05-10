package models;

import com.google.gson.Gson;
import utilities.Fichero;

import java.io.*;
import java.lang.reflect.Type;

public class Json {
    private String direccion;
    private String nombre;
    private Reader reader;
    private Gson gson;

    /**
     * Constructor Json
     *
     * @param nombre String nombre del archivo json
     */
    public Json(String nombre) {
        this.nombre = nombre;
        this.direccion = "src/json/";

        this.gson = new Gson();

        Fichero.crearDirectorio(this.direccion);
    }

    /**Metodo escribir
     *
     * @param json String contenido json
     */
    public void escribirJson(String json) {
        try {
            Writer writer = new FileWriter(this.direccion + this.nombre);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Metodo leerJson
     *
     * @param type Type tipo de formato de estructura
     * @return objeto del tipo que se designo por parametro (si no encuetra ningun contenido en el json retorna {@code null})
     */
    public <T> T leerJson(Type type) {
        try {
            Reader reader = new FileReader(this.direccion + this.nombre);
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            escribirJson("[]");
        }
        return leerJson(type);
    }
}
