package utilities;

import java.io.File;

public class Fichero {
    /**
     * Crea un directorio en la ruta especificado
     *
     * @param patchname String ruta del directorio y nombre
     */
    public static void crearDirectorio(String patchname) {
        File directorio = new File(patchname);
        if (!directorio.exists()) directorio.mkdir();
    }
}
