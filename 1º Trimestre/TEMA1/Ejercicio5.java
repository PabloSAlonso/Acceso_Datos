import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la ruta");
        String archivo = sc.nextLine();

        System.out.println("Ingresa la cadena");
        String letras = sc.nextLine();

        try (Scanner sc2 = new Scanner(new File(archivo))) {
            int lineas = 1;
            while (sc.hasNext()) {
                String lineaActual = sc2.nextLine();
                
                String[] palabras = lineaActual.split("\\W+");
                
                for (String palabra : palabras) {
                    if (palabra.equals(letras)) {
                        System.out.println("La palabra " + letras + " sale en la linea:" + lineas);
                    }
                }
                
                lineas++;

            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        sc.close();

    }
}
