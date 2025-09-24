import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio9 {

    public static void altaAlumnos(File fichero, ArrayList<Alumno> datos) {
        Alumno alumno = new Alumno();
        FileOutputStream fos = null;
        DataOutputStream out = null;
        try {
            fos = new FileOutputStream(fichero);
            out = new DataOutputStream(fos);
            for (Alumno al : datos) {
                out.writeInt(al.getCodigo());
                out.writeUTF(al.getNombre());
                out.writeFloat(al.getAltura());
            }
            datos.add(alumno);
        } catch (IOException e) {
            System.out.println("Error: " +
                    e.getLocalizedMessage());
        } finally {
            if (out != null)
                out.close();
            if (fos != null)
                fos.close();
        }
    }
    public static void consultarAlumnos(){

    }

}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    int opcion;
    do {
        System.out.println("Elige una opcion");
        System.out.println("1.- Dar de alta alumnos");
        System.out.println("2.- Consultar alumnos");
        System.out.println("3.- Modificar alumnos");
        System.out.println("4.- Borrar alumnos");
        System.out.println("5.- SALIR");
        opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                // Código opcion 1

                break;
            case 2:
                // Código opcion 2

                break;
            case 3:
                // Código opcion 3

                break;
            case 4:
                // Código opcion 4

                break;
            case 5:
                // Código opcion 5
                System.out.println("Chaaaaao");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    } while (opcion != 5);
}
