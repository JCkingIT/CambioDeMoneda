package utilities;

import java.io.File;

public class Fichero {
    public static void crearDirectorio(String patchname) {
        File directorio = new File(patchname);
        if (!directorio.exists()) directorio.mkdir();
    }
}
