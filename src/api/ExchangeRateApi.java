package api;

import confing.Configuracion;

public class ExchangeRateApi extends Api {

    /**
     * Constructor Api,
     * inicializa las variables {@code url} y {@code key} desde el .env para realizar las consultas respectivas a la API
     */
    public ExchangeRateApi() {
        url = Configuracion.apiUrl;
        key = Configuracion.apiKey;
    }

    /**
     * Metodo consultarMonedas
     *
     * @return {@code json} String lista de monedas
     */
    public String consultarMonedas() {
        String url = this.url + this.key + "/codes";
        return consultar(url);
    }

    /**
     * Metodo consultarCambio
     *
     * @param monedaActula String nombre de la moneda
     * @param monedaCambio String nombre de la moneda
     * @return {@code json} String valor de moneda de comverción
     */
    public String consultarCambio(String monedaActula, String monedaCambio) {
        String url = this.url + this.key + "/pair/" + monedaActula + "/" + monedaCambio;
        return consultar(url);
    }
}