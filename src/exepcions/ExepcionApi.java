package exepcions;

import com.google.gson.Gson;
import config.Configuracion;
import records.ErrorApi;

public class ExepcionApi {

    /**
     * Verifica la respuesta de la API con algunas expciones
     * @param json String respuesta de la API
     * @return {@code true} si a surgido un error con la consulta, caso contrario sera {@code false}
     */
    static public boolean verificacion(String json) {
        Gson gson = new Gson();
        ErrorApi respuesta = gson.fromJson(json, ErrorApi.class);
        if (respuesta.result().equals("success")) return false;

        switch (respuesta.error_type()) {
            case "invalid-key":
                System.out.println("Clave API no válida: "+ Configuracion.apiKey);
                break;
            case "inactive-account":
                System.out.println("Direccion de correo electrónico no fue confirmado");
                break;
            case "quota-reached":
                System.out.println("Su cuenta ya alcanzo la cantidad de solicitudes permitidas por su plan");
                break;
            default:
                System.out.println(respuesta.error_type());
        }
        return true;
    }
}
