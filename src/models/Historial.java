package models;

import utilities.Numero;

public class Historial {
    private String fecha;
    private String hora;
    private String monedaBase;
    private String monedaBasePais;
    private double valorBase;
    private String monedaCambio;
    private String monedaCambioPais;
    private double valorCambio;
    private double valorFinal;

    public Historial() {
    }

    /**
     * Constructor Historial
     *
     * @param fecha             String fecha en que se realizo el cambio
     * @param hora              String hora en que se realizo el cambio
     * @param monedaBase        String nombre de la moneda
     * @param monedaBasePais    String nombre del país de la moneda
     * @param valorBase         double valor de la moneda
     * @param monedaCambion     String nombre de la moneda de cambion
     * @param monedaCambionPais String nombre del país de la moneda de cambion
     * @param valorCambio       double valor de la moneda de cambio
     */
    public Historial(String fecha, String hora, String monedaBase, String monedaBasePais, double valorBase, String monedaCambion, String monedaCambionPais, double valorCambio) {
        this.fecha = fecha;
        this.hora = hora;
        this.monedaBase = monedaBase;
        this.monedaBasePais = monedaBasePais;
        this.valorBase = Numero.decimalFormat(valorBase);
        this.monedaCambio = monedaCambion;
        this.monedaCambioPais = monedaCambionPais;
        this.valorCambio = Numero.decimalFormat(valorCambio);
        this.valorFinal = Numero.decimalFormat(valorFinalMoneda(this.valorBase, this.valorCambio));
    }

    /**
     * Metodo getFecha
     *
     * @return {@code String} fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Metodo getMonedaBase
     *
     * @return {@code String} nombre de la moneda base
     */
    public String getMonedaBase() {
        return monedaBase;
    }

    /**
     * Metodo setMonedaBase
     *
     * @param monedaBase String nombre de moneda base
     */
    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    /**
     * Metodo getMonedaBase
     *
     * @return {@code String} nombre del pais de la moneda
     */
    public String getMonedaBasePais() {
        return monedaBasePais;
    }

    /**
     * Metodo setMonedaBase
     *
     * @param monedaBasePais String nombre del país de la moneda
     */
    public void setMonedaBasePais(String monedaBasePais) {
        this.monedaBase = monedaBasePais;
    }

    /**
     * Metodo getValorBase
     *
     * @return {@code double} valor de la moneda base
     */
    public double getValorBase() {
        return valorBase;
    }

    /**
     * Metodo setValorBase
     *
     * @param valorBase double valor de la moneda base
     */
    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    /**
     * Metodo getValorCambio
     *
     * @return {@code double} valor de la moneda cambio
     */
    public double getValorCambio() {
        return valorCambio;
    }

    /**
     * Metodo setValorCambio
     *
     * @param valorCambio double valor de la moneda de cambio
     */
    public void setValorCambio(double valorCambio) {
        this.valorCambio = valorCambio;
    }

    /**
     * Metodo getMonedaCambio
     *
     * @return {@code double} nombre de la moneda de cambio
     */
    public String getMonedaCambio() {
        return monedaCambio;
    }

    /**
     * Metodo setMonedaCambio
     *
     * @param monedaCambio String nombre de la moneda de cambio
     */
    public void setMonedaCambio(String monedaCambio) {
        this.monedaCambio = monedaCambio;
    }

    /**
     * Metodo getMonedaCambio
     *
     * @return {@code double} nombre del país de la moneda de cambio
     */
    public String getMonedaCambioPais() {
        return monedaCambioPais;
    }

    /**
     * Metodo setMonedaCambio
     *
     * @param monedaCambioPais String nombre del país de la moneda de cambio
     */
    public void setMonedaCambioPais(String monedaCambioPais) {
        this.monedaCambioPais = monedaCambioPais;
    }

    /**
     * Metodo getValorFinal
     *
     * @return {@code double} valor de la converción de la moneda
     */
    public double getValorFinal() {
        return valorFinal;
    }

    /**
     * Metodo getHora
     *
     * @return {@code String} hora
     */
    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "fecha='" + fecha +
                ", hora='" + hora +
                ", monedaBase='" + monedaBase +
                ", valorBase=" + valorBase +
                ", monedaCambion='" + monedaCambio +
                ", valorCambio=" + valorCambio +
                ", valorFinal='" + valorFinal;
    }

    /**
     * Metodo valorFinalMoneda
     *
     * @param base   double valor de la moneda base
     * @param cambio double valor de la moneda cambio
     * @return {@code double } valor de la converción de la moneda
     */
    private double valorFinalMoneda(double base, double cambio) {
        return (base * cambio);
    }
}
