import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ej5 {
    public static void main(String[] args) throws FileNotFoundException{
        
        try (Scanner sc = new Scanner(new File("dos.txt"))) {
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}
