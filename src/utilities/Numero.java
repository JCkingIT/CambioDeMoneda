package utilities;

import java.text.DecimalFormat;

public class Numero {

    /**
     * Verifica si una cadena de texto esta compuesto solo de numeros
     * @param numero String
     * @return {@code true} si la cadena solo contiene solo numeros de lo contrario sera {@code false}
     */
    public static boolean isInteger(String numero){
        try {
            Integer.parseInt(numero);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Formatea un número decimal con una precicion de dos decimales
     * @param num double
     * @return {@code double} decimal redondeado con una precicion de dos decimales
     */
    public static double decimalFormat (double num){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(num));
    }
}
