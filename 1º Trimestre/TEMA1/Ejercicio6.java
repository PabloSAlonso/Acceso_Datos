import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {

    static String leerArchivo(String nomArchivo) throws FileNotFoundException {
        String contenido = "";
        try (Scanner sc = new Scanner(new File(nomArchivo))) {
            while (sc.hasNext()) {
                contenido += sc.nextLine();
            }
        }
        return contenido;
    }

    static int determinarNumArchivos(String contenido, int cantidadCaracteres) {
        return contenido.length() / cantidadCaracteres;
    }

    // static String[] dividirContenidoArchivos(String contenidoArchivo, int
    // numArchivos, int numCaracteres) {
    // return contenidoArchivo.splitWithDelimiters("", numCaracteres);
    // }

    static String[] dividirContenidoArchivos(String contenidoArchivo, int numArchivos, int numCaracteres) {
        int longitudTotal = contenidoArchivo.length();
        int numPartes = (int) Math.ceil((double) longitudTotal / numCaracteres);
        String[] partes = new String[numPartes];

        for (int i = 0; i < numPartes; i++) {
            int inicio = i * numCaracteres;
            int fin = Math.min(inicio + numCaracteres, longitudTotal);
            partes[i] = contenidoArchivo.substring(inicio, fin);
        }

        return partes;
    }

    static void escribirArchivo(String[] contenidoArchivos) throws IOException {
        for (int i = 0; i < contenidoArchivos.length; i++) {
            try (FileWriter fw = new FileWriter((String.format("archivo%d.txt", i)))) {
                fw.write(contenidoArchivos[i]);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int numMaxCaracteres = 5;
        escribirArchivo((dividirContenidoArchivos(leerArchivo("ejemplo.txt"),
                determinarNumArchivos(leerArchivo("ejemplo.txt"), numMaxCaracteres), numMaxCaracteres)));
    }
}
