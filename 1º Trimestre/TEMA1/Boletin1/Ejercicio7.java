import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio7 {

    static ArrayList<String> lineas;

    public static void escribirArchivo(String nomArchivo, String archivoBase, ArrayList<String> lineas)
            throws IOException {
        try (FileWriter fichOut = new FileWriter(nomArchivo)) {
            for (String linea : lineas) {
                fichOut.write(linea);
                fichOut.write(System.getProperty("line.separator"));
            }
            System.out.println("Archivo: " + nomArchivo + " creado a partir de " + archivoBase);
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static String leerArchivo(String nomArchivo) throws IOException {
        String palabras = "";
        try (Scanner sc = new Scanner(new File(nomArchivo))) {
            int numLineas = 0;
            int numPalabras = 0;
            lineas = new ArrayList<>();
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                lineas.add(linea);
                numLineas++;
                palabras += linea.split("\\W+");
                numPalabras += palabras.length();
            }
            System.out.println("Numero de lineas del archivo:" + numLineas);
            System.out.println("Numero palabras: " + numPalabras);
        } catch (IOException e) {
            System.out.println("error");
        }
        return palabras;
    }

    public static void Menu(String nomArchivo, String opcion) throws IOException {
        // Scanner sc2 = new Scanner(System.in);
        // System.out.println("Escribe el nº del archivo: ");
        // nomArchivo = sc2.nextLine();
        // System.out.println("Dame una opcion:");
        // opcion = sc2.nextLine();
        // sc2.close();

        System.out.println("Opcion seleccionada:" + opcion);

        switch (opcion) {
            case "n":
                leerArchivo("ejemplo.txt");

                break;

            case "A":
                leerArchivo(nomArchivo);
                Collections.sort(lineas);
                escribirArchivo("holaa.txt", "ejemplo.txt", lineas);

                break;

            case "D":
                leerArchivo(nomArchivo);
                Collections.sort(lineas, Collections.reverseOrder());
                escribirArchivo("holaa.txt", "ejemplo2.txt", lineas);

                break;

            case "a":
                leerArchivo(nomArchivo);
                Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER);
                escribirArchivo("holaa.txt", "ejemplo2.txt", lineas);
                break;

            case "d":
                leerArchivo(nomArchivo);
                Collections.sort(lineas, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
                escribirArchivo("holaa.txt", "ejemplo2.txt", lineas);
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        Menu("ejemplo.txt", "A");
    }
}
