package models;

import utilities.Numero;

public class Historial {
    private String fecha;
    private String hora;
    private String monedaBase;
    private double valorBase;
    private String monedaCambion;
    private double valorCambio;
    private double valorFinal;

    public Historial(){}

    public Historial(String fecha,String hora, String monedaBase, double valorBase, String monedaCambion, double valorCambio) {
        this.fecha = fecha;
        this.hora = hora;
        this.monedaBase = monedaBase;
        this.valorBase = Numero.decimalFormat(valorBase);
        this.monedaCambion = monedaCambion;
        this.valorCambio = Numero.decimalFormat(valorCambio);
        this.valorFinal = Numero.decimalFormat(valorFinalMoneda(this.valorBase,this.valorCambio));
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorCambio() {
        return valorCambio;
    }

    public void setValorCambio(double valorCambio) {
        this.valorCambio = valorCambio;
    }

    public String getMonedaCambion() {
        return monedaCambion;
    }

    public void setMonedaCambion(String monedaCambion) {
        this.monedaCambion = monedaCambion;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "fecha='" + fecha +
                ", hora='" + hora +
                ", monedaBase='" + monedaBase +
                ", valorBase=" + valorBase +
                ", monedaCambion='" + monedaCambion +
                ", valorCambio=" + valorCambio +
                ", valorFinal='" + valorFinal;
    }

    private double valorFinalMoneda(double base, double cambio) {
        return (base * cambio);
    }
}
