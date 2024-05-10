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
            String api = sc.nextLine();

            if (index != 0) api = sc.nextLine();
            else index++;
            return api.substring(8);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
