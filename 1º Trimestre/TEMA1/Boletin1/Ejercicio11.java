import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio11 {

    public static void leerArchivoConBIS(char buffer[]) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prueba100MB.txt"))) {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prueba100MB_2.txt"))) {
                int i;
                while ((i = bis.read()) != -1) {
                    bos.write(bis.read());
                    buffer[i] = (char) bis.read();
                }
            }
        }
    }
    public static void leerArchivoConFIS(char buffer[]) throws IOException {
        try (FileInputStream fis = new FileInputStream("prueba100MB.txt")) {
            try (FileOutputStream fos = new FileOutputStream("prueba100MB_2.txt")) {
                int i;
                while ((i = fis.read()) != -1) {
                    fos.write(fis.read());
                    buffer[i] = (char) fis.read();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> tamañoBufferPrueba = new ArrayList<>();
        tamañoBufferPrueba.add(10);
        tamañoBufferPrueba.add(100);
        tamañoBufferPrueba.add(1000);
        double inicio;
        double finalizar;
        for (int tamaño : tamañoBufferPrueba) {
            inicio = System.currentTimeMillis();
            leerArchivoConBIS(new char[tamaño]);
            finalizar = System.currentTimeMillis();
            System.out.println(finalizar - inicio + " -> Tiempo tardado con buffer de -> " + tamaño + "usando BIS");
            // inicio = System.currentTimeMillis();
            // leerArchivoConFIS(new char[tamaño]);
            // finalizar = System.currentTimeMillis();
            // System.out.println(finalizar - inicio + " -> Tiempo tardado con buffer de -> " + tamaño + "usando FIS");
        }
    }
}
