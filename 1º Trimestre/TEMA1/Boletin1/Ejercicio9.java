import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio9 {

    public static void altaAlumnos(String fichero, ArrayList<Alumno> datos) throws IOException {
        Scanner al = new Scanner(System.in);
        System.out.println("codigo, nombre y altura de tu nuevo alumno");
        int codigo = al.nextInt();
        al.nextLine();
        String nombre = al.nextLine();
        float altura = al.nextFloat();
        Alumno alumno = new Alumno(codigo, nombre, altura);
        FileOutputStream fos = new FileOutputStream(new File(fichero), true);
        try (DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(alumno.getCodigo());
            dos.writeUTF(alumno.getNombre());
            dos.writeFloat(alumno.getAltura());
        }
        datos.add(alumno);
    }

    public static void consultarAlumnos(ArrayList<Alumno> datos) throws IOException {
        FileInputStream fis = new FileInputStream("alumnos.dat");
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                System.out.println("Codigo: " + dis.readInt());
                System.out.println("Nombre: " + dis.readUTF());
                System.out.println("Altura: " + dis.readFloat());
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        }
    }

    public static void modificarAlumnos(int codigoAlumno, String nomArchivo) throws IOException {
        Scanner md = new Scanner(System.in);
        File archivoPrincipal = new File(nomArchivo);
        FileInputStream fis = new FileInputStream(nomArchivo);
        File archivoTemporal = new File("temporal.dat");
        FileOutputStream fos = new FileOutputStream(archivoTemporal, true);
        boolean flag = false;
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                int num = dis.readInt();
                dis.readUTF();
                dis.readFloat();
                try (DataOutputStream dos = new DataOutputStream(fos)) {
                    if (codigoAlumno == num) {
                        System.out.println("h");
                        dos.writeInt(codigoAlumno);
                        System.out.print("Nombre nuevo: ");
                        dos.writeUTF(md.nextLine());
                        System.out.print("Altura nueva: ");
                        dos.writeFloat(md.nextFloat());
                        flag = true;
                    }
                    dos.writeInt(dis.readInt());
                    dos.writeUTF(dis.readUTF());
                    dos.writeFloat(dis.readFloat());
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");
        }
        if (flag) {
            if (archivoPrincipal.delete()) {
                archivoTemporal.renameTo(archivoPrincipal);
            }
        } else {
            archivoTemporal.delete();
        }
    }

    public static void eliminarAlumnos(int codigoAlumno, String nombreArchivo) throws IOException {
        File archivoPrincipal = new File(nombreArchivo);
        FileInputStream fis = new FileInputStream(nombreArchivo);
        File archivoTemporal = new File("temporal.dat");
        FileOutputStream fos = new FileOutputStream(archivoTemporal, true);
        boolean flag = false;
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                int num = dis.readInt();
                dis.readUTF();
                dis.readFloat();
                try (DataOutputStream dos = new DataOutputStream(fos)) {

                    if (codigoAlumno != num) {
                        System.out.println("entra");
                        dos.writeInt(dis.readInt());
                        dos.writeUTF(dis.readUTF());
                        dos.writeFloat(dis.readFloat());
                    }
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin de archivo");
        }

        archivoPrincipal.delete();
        archivoTemporal.renameTo(archivoPrincipal);

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        int opcion = 0;
        do {
            System.out.println("Elige una opcion");
            System.out.println("1.- Dar de alta alumnos");
            System.out.println("2.- Consultar alumnos");
            System.out.println("3.- Modificar alumnos");
            System.out.println("4.- Borrar alumnos");
            System.out.println("5.- SALIR");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    // Código opcion 1
                    altaAlumnos("alumnos.dat", listaAlumnos);
                    break;
                case 2:
                    // Código opcion 2
                    consultarAlumnos(listaAlumnos);
                    break;
                case 3:
                    // Código opcion 3
                    System.out.println("Codigo del alumno a modificar:");
                    int c = sc.nextInt();
                    modificarAlumnos(c, "alumnos.dat");
                    break;
                case 4:
                    // Código opcion 4
                    System.out.println("Codigo del alumno a eliminar:");
                    c = sc.nextInt();
                    eliminarAlumnos(c, "alumnos.dat");
                    break;
                case 5:
                    // Código opcion 5
                    System.out.println("Chaaaaao");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 5);
    }
}
