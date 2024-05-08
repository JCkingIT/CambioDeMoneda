package utilities;

import java.text.DecimalFormat;

public class Numero {
    public static boolean isInteger(String numero){
        try {
            Integer.parseInt(numero);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static double decimalFormat (double num){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(num));
    }
}
