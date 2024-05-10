package models;

/**
 * Clase Moneda.
 *
 * @author José Israel Castro De La Torres
 * @version 7/05/2024
 */
public class Moneda {
    private String moneda;
    private String pais;

    /**
     * Constructor Moneda
     */
    public Moneda() {
    }

    /**
     * Constructor Moneda
     *
     * @param moneda String -> nombre de la moneda
     * @param pais   String -> nombre del pais que corresponda a la moneda
     */
    public Moneda(String moneda, String pais) {
        this.moneda = moneda;
        this.pais = pais;
    }

    /**
     * Constructor Moneda
     *
     * @param otraMoneda Moneda objeto de tipo Moneda
     */
    public Moneda(Moneda otraMoneda) {
        this.moneda = otraMoneda.moneda;
        this.pais = otraMoneda.pais;
    }

    /**
     * Metodo getMoneda
     *
     * @return moneda nombre de la moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Metodo setMonedsa
     *
     * @param moneda String nombre de la moneda
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * Metodo getPais
     *
     * @return pais String nombre del pais que corresponda a la moneda
     */
    public String getPais() {
        return pais;
    }

    /**
     * Metodo setPais
     *
     * @param pais String nombre del pais que corresponda a la moneda
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "moneda='" + moneda +
                ", pais='" + pais;
    }
}
