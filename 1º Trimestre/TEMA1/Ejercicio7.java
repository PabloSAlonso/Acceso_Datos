import java.io.File;
import java.util.Scanner;

public class Ejercicio7 {

    public static void Ej7(String nomArchivo, char opcion) {

        do {
            System.out.println("Opcion seleccionada:" + opcion);
            switch (opcion) {
                case 'n':
                try (Scanner sc = new Scanner(new File(nomArchivo))){
                    int numLineas = 0;
                    String[] palabras;
                    while (sc.hasNextLine()) {
                        numLineas++;
                        if (sc.nextLine().contains(" ")) {
                            
                        }
                    }
                    System.out.println("Numero de lineas del archivo:" + numLineas);
                } catch (Exception e) {
                    System.out.println("error");
                }

                    break;
                case 'A':

                    break;

                case 'D':

                    break;

                case 'a':

                    break;

                case 'd':

                    break;

                default:
                    break;
            }
        } while (opcion != 'e');
    }

    public static void main(String[] args) {

    }
}
