package models;

public class Moneda {
    private String moneda;
    private String pais;

    public Moneda() {}

    public Moneda(String moneda, String pais) {
        this.moneda = moneda;
        this.pais = pais;
    }

    public Moneda(Moneda otraMoneda){
        this.moneda = otraMoneda.moneda;
        this.pais = otraMoneda.pais;
    }

    public Moneda(String moneda, String pais, double valor) {
        this.moneda = moneda;
        this.pais = pais;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "moneda='" + moneda +
                ", pais='" + pais;
    }
}
