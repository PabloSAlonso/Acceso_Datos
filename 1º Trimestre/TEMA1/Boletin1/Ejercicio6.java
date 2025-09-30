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

    // static int determinarNumArchivos(String contenido, int cantidadCaracteres) {
    //     return contenido.length() / cantidadCaracteres;
    // }

    static String[] dividirContenidoArchivos(String contenidoArchivo, int numArchivos, int numCaracteres) {
        int longitudTotal = contenidoArchivo.length();
        String[] partes = new String[numArchivos];
        int inicio = 0;

        for (int i = 0; i < numArchivos; i++) {
            inicio = i * numCaracteres;
            int fin = Math.min(inicio + numCaracteres, longitudTotal);
            partes[i] = contenidoArchivo.substring(inicio, fin);
            inicio = fin;
        }

        return partes;
    }

    static void escribirArchivo(String[] contenidoArchivos) throws IOException {
        for (int i = 0; i < contenidoArchivos.length; i++) {
            try (FileWriter fw = new FileWriter((String.format("archivo%d.txt", i)))) {
                fw.write(contenidoArchivos[i]);
            } catch (IOException e) {

            }
        }
    }

    static String[] dividirLineasArchivos(String nomArchivo, int numFilas) throws IOException {
        String contenidoArchivo[] = new String[] {};
        int j = 1;
        Scanner sc = new Scanner(new File(nomArchivo));
        while (sc.hasNext()) {
            try (FileWriter fw = new FileWriter(new File("archivoLineas" + (j++) + ".txt"))) {
                for (int i = 0; i < numFilas; i++) {
                    fw.write(sc.nextLine());
                    fw.write(System.lineSeparator());
                }
            }

        }
        return contenidoArchivo;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int numCaracteres = 50;
        int numArchivos = 3;
        int numLineas = 2;

        escribirArchivo(dividirContenidoArchivos(leerArchivo("1º Trimestre\\TEMA1\\ejemplo.txt"),
        numArchivos, numCaracteres));
        
        // escribirArchivo(dividirLineasArchivos("1º Trimestre\\TEMA1\\ejemplo.txt", numLineas));

    }

}
