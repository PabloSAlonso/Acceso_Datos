package repaso_examen;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Repaso {
    public static String Ejercicio3extra(String fichero, String palabra) throws IOException {
        int contador = 0;
        File archivo = new File(fichero);
        Scanner sc = new Scanner(archivo);
        String[] buffer = new String[50];
        int i = 0;
        while (sc.hasNext()) {
            buffer[i] = sc.next().split("\\W+")[0];
            if (buffer[i].equals(palabra)) {
                contador++;
            }
            i++;
        }
        return String.format("La palabra %s ha aparecido %d veces", palabra, contador);
    }
    
    public static void Ejercicio4extra(String fichero, String palabraEliminar){
        
    }


    public static void main(String[] args) throws FileNotFoundException, IOException, EOFException {
        // System.out.println(Ejercicio3extra("dos.txt", "celta"));
    }

}
