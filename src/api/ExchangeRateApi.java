package api;

import confing.Configuracion;
import exepcions.ExepcionApi;

public class ExchangeRateApi extends Api {

    /**
     * Constructor Api,
     * inicializa las variables {@code url} y {@code key} desde el .env para realizar las consultas respectivas a la API
     */
    public ExchangeRateApi() {
        this.url = Configuracion.apiUrl;
        this.key = Configuracion.apiKey;
    }

    /**
     * Metodo consultarMonedas
     *
     * @return {@code json} String lista de monedas
     */
    public String consultarMonedas() {
        String json = consultar(this.url + this.key + "/codes");
        if(ExepcionApi.verificacion(json)) System.exit(0);
        return json;
    }

    /**
     * Metodo consultarCambio
     *
     * @param monedaActula String nombre de la moneda
     * @param monedaCambio String nombre de la moneda
     * @return {@code json} String valor de moneda de comverción
     */
    public String consultarCambio(String monedaActula, String monedaCambio) {
        String json = consultar(this.url + this.key + "/pair/" + monedaActula + "/" + monedaCambio);;

        if(ExepcionApi.verificacion(json)) System.exit(0);
        return json;
    }
}