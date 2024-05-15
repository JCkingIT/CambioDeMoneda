package confing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Configuracion {
    static public String apiUrl = env();
    static public String apiKey = env();
    static private int index = 0;

    static private String env() {
        File reader = null;
        try {
            reader = new File("src/.env");
            Scanner sc = new Scanner(reader);

            String [] env =  sc.nextLine().split("=");

            if (index != 0) {
                env = sc.nextLine().split("=");
                return (env[0].equals("API_KEY"))? env[1]:"";
            }
            else {
                index++;
                return (env[0].equals("API_URL"))? env[1]:"";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
