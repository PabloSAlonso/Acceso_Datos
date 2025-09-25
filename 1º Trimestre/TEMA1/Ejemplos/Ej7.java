import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ej7 {


    public static void main(String[] args) throws IOException {
        try (FileInputStream fin = new FileInputStream("ejemplo.txt");
                FileOutputStream fout = new FileOutputStream("nuevo.dat", true)) {// LEER Y ESCRIBIR
            int i;
            while ((i = fin.read()) != -1) {
                fout.write(i);
            }

        } catch (IOException e) {
            System.out.println("error");
        }

    }
}
