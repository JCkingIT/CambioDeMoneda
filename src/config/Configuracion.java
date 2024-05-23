package config;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuracion {
    static private Dotenv env = Dotenv.load();
    static public String apiUrl = env.get("API_URL");
    static public String apiKey = env.get("API_KEY");
}
