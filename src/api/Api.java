package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    private String url;
    private String key;

    public Api(){
        this.url = "https://v6.exchangerate-api.com/v6/";
        this.key = "823d2dbb496ac020e7611b14";
    }

    private String consultar (String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (IOException | InterruptedException e){
            System.out.println("Surgion un erro inesperado: "+ e.getMessage());
            return null;
        }
    }

    public String consultarMonedas(){
        String url = this.url + this.key + "/codes";
        return consultar(url);
    }

    public String consultarCambio(String monedaActula, String monedaCambio){
        String url = this.url + this.key + "/pair/" +monedaActula + "/" +monedaCambio;
        return consultar(url);
    }
}
