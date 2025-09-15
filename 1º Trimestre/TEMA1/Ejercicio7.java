import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio7 {

    public static void Ej7_n(String nomArchivo, String opcion) {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Escribe el nº del archivo: ");
        nomArchivo = sc2.nextLine();
        System.out.println("Dame una opcion:");
        opcion = sc2.nextLine();
        sc2.close();
        System.out.println("Opcion seleccionada:" + opcion);

        switch (opcion) {
            case "n":
                try (Scanner sc = new Scanner(new File(nomArchivo))) {

                    int numLineas = 0;
                    int numPalabras = 0;

                    while (sc.hasNextLine()) {
                        String linea = sc.nextLine();
                        numLineas++;
                        String[] palabras = linea.split("\\W+");
                        numPalabras += palabras.length;
                    }
                    
                    System.out.println("Numero de lineas del archivo:" + numLineas);
                    System.out.println("Numero palabras: " + numPalabras);

                } catch (IOException e) {
                    System.out.println("error");
                }

                break;
            case "A":

                break;

            case "D":

                break;

            case "a":

                break;

            case "d":

                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        Ej7_n("dos.txt", "n");
    }
}
