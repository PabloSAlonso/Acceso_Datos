import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio7 {
    // static ArrayList<String> lineas = new ArrayList<>();

    public static void escribirArchivo(String nomArchivo, String cadena) throws IOException {
        try (FileWriter fichOut = new FileWriter(nomArchivo)) {
            for (int i = 0; i < cadena.length(); i++) {
                fichOut.write(cadena.charAt(i));
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static String leerArchivo(String nomArchivo) throws IOException {
        String palabras = "";
        try (Scanner sc = new Scanner(new File(nomArchivo))) {
            int numLineas = 0;
            int numPalabras = 0;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                // lineas.add(linea);
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

    public static void Ej7_n(String nomArchivo, String opcion) throws IOException {
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
                escribirArchivo("holaa.txt", leerArchivo("ejemplo.txt"));
                
                break;

            case "D":
                escribirArchivo("holaa.txt", leerArchivo("ejemplo.txt"));

                break;

            case "a":
                escribirArchivo("holaa.txt", leerArchivo("ejemplo.txt"));

                break;

            case "d":
                escribirArchivo("holaa.txt", leerArchivo("ejemplo.txt"));

                break;

            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        Ej7_n("ejemplo.txt", "n");
    }
}
