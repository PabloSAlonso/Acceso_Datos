import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio11 {

    public static void leerArchivoConBIS() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prueba100MB.txt"))) {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prueba100MB_2.txt"))) {
                int i;
                while ((i = bis.read()) != -1) {
                    bos.write(bis.read());
                    
                }
            }
        }
    }
    public static void leerArchivoConFIS(byte buffer[]) throws IOException {
        try (FileInputStream fis = new FileInputStream("prueba100MB.txt")) {
            try (FileOutputStream fos = new FileOutputStream("prueba100MB_2.txt")) {
                int i;
                while ((i = fis.read(buffer)) != -1) {
                    fos.write(buffer,0,i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> tamañoBufferPrueba = new ArrayList<>();
        tamañoBufferPrueba.add(1000);
        tamañoBufferPrueba.add(10000);
        tamañoBufferPrueba.add(10000);
        double inicio;
        double finalizar;
        inicio = System.currentTimeMillis();
        leerArchivoConBIS();
        finalizar = System.currentTimeMillis();
        System.out.println(finalizar - inicio + " -> Tiempo tardado con buffer usando BIS");
        for (int tamaño : tamañoBufferPrueba) {
            inicio = System.currentTimeMillis();
            leerArchivoConFIS(new byte[tamaño]);
            finalizar = System.currentTimeMillis();
            System.out.println(finalizar - inicio + " -> Tiempo tardado con buffer de -> " + tamaño + "usando FIS");
        }
    }
}
