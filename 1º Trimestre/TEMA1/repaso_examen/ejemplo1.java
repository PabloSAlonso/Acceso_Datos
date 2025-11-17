package repaso_examen;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ejemplo1 {
    public static String readTab(String archivo, int pos) throws FileNotFoundException, IOException, EOFException {
        try (Scanner sc = new Scanner(new File(archivo))) {
            String[] cadaElemento;
            int val = 0;
            double precio = 0.0;
            String nombre = "";
            int contarLineas = 1;
            String primeraLinea = sc.nextLine();
            while (sc.hasNextLine()) {
                cadaElemento = sc.nextLine().split("\t");
                if (pos == contarLineas) {
                    val = Integer.parseInt(cadaElemento[0]);
                    precio = Double.parseDouble(cadaElemento[1]);
                    nombre = cadaElemento[2];
                }
                contarLineas++;
            }
            return String.format("Nombre: %s, Valoracion/precio: %f", nombre, val * precio);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, EOFException {
        System.out.println(readTab("repaso_examen\\juegos.txt", 2));
    }

}
