import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ej6 {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("dos.txt",true))) {
            pw.println("QUE PASA CHAVALES");
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
