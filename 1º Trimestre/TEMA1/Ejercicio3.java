import java.io.FileReader;
import java.io.IOException;

public class Ejercicio3 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("dos.txt")) {

            int i;
            int contador = 0;
            char letra = 'a';
            while ((i = fr.read()) != -1) {
                if (letra == i) {
                    contador++;
                }
            }
            System.out.println("Numero de a: " + contador);

        } catch (IOException e) {
            System.out.println("error");
        }

    }
}
