package utilities;

import java.text.DecimalFormat;

public class Numero {

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
