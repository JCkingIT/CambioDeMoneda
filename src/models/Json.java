package models;

import com.google.gson.Gson;
import utilities.Fichero;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Json {
    private String direccion;
    private String nombre;
    private Reader reader;
    private Gson gson;

    public Json(String nombre) {
        this.nombre = nombre;
        this.direccion = "src/json/";

        this.gson = new Gson();

        Fichero.crearDirectorio(this.direccion);
    }

    public void escribirJson(String json) {
        try {
            Writer writer = new FileWriter(this.direccion + this.nombre);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public <T> T leerJson(Type type){
        try{
            Reader reader = new FileReader(this.direccion+this.nombre);
            return gson.fromJson(reader,type);
        } catch (FileNotFoundException e) {
            escribirJson("[]");
            return leerJson(type);
        }
    }
}
