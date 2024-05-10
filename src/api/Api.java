package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    protected String url;
    protected String key;

    /**
     * Metodo consultar,
     * contiene la peticion Http
     *
     * @param url String direccion de la api (url)
     * @return {@code json} String repusta de resApi, si surge un erro retorna {@code null}
     */
    protected String consultar(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("Surgion un error inesperado: " + e.getMessage());
            return null;
        }
    }
}
