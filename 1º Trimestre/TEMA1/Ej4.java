import java.io.FileWriter;
import java.io.IOException;

public class Ej4 {
    public static void main(String[] args) {
        String cad = "Girona 0-1 Celta";
        //Filewriter va de caracter en caracter
        try (FileWriter fw = new FileWriter("dos.txt", true)){
            for (int i = 0; i < cad.length(); i++) {
                fw.write(cad.charAt(i));
            }
            fw.write(System.lineSeparator());
        } catch (IOException i){
            System.out.println("error");
        }
        
    }
}
