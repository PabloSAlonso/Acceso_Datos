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
        Alumno alumno = new Alumno();
        FileOutputStream fos = null;
        DataOutputStream out = null;
        try {
            fos = new FileOutputStream(new File(fichero), true);
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

    public static void consultarAlumnos(ArrayList<Alumno> datos) throws IOException {
        FileInputStream fis = new FileInputStream("alumnos.dat");
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                dis.readInt();
                dis.readUTF();
                dis.readFloat();
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        }
    }

    public static void modificarAlumnos(int codigoAlumno, String nomArchivo) throws IOException {
        Scanner sc = new Scanner(System.in);
        File archivoPpal = new File(nomArchivo);
        FileInputStream fis = new FileInputStream(nomArchivo);
        File archivoTemp = new File("temporal.dat");
        FileOutputStream fos = new FileOutputStream(archivoTemp);
        boolean flag = false;
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                dis.readInt();
                dis.readUTF();
                dis.readFloat();
                try (DataOutputStream dos = new DataOutputStream(fos)) {
                    if (codigoAlumno == dis.readInt()) {
                        dos.writeInt(codigoAlumno);
                        System.out.println("Nombre: ");
                        dos.writeUTF(sc.nextLine());
                        System.out.println("Altura: ");
                        dos.writeDouble(sc.nextDouble());
                        flag = true;
                    }
                    dos.writeInt(dis.readInt());
                    dos.writeUTF(dis.readUTF());
                    dos.writeDouble(dis.readDouble());

                } catch (EOFException e) {
                    System.out.println("Fin archivo");
                }
                if (flag) {
                    if (archivoPpal.delete()) {
                        archivoTemp.renameTo(archivoPpal);
                    }
                } else {
                    archivoTemp.delete();
                }
            }
        }
    }

    public static void eliminarAlumnos(int codigoAlumno, String nomArchivo) throws IOException {
        Scanner sc = new Scanner(System.in);
        File archivoPpal = new File(nomArchivo);
        FileInputStream fis = new FileInputStream(nomArchivo);
        File archivoTemp = new File("temporal.dat");
        FileOutputStream fos = new FileOutputStream(archivoTemp);
        boolean flag = false;
        try (DataInputStream dis = new DataInputStream(fis)) {
            while (true) {
                try (DataOutputStream dos = new DataOutputStream(fos)) {
                    if (codigoAlumno != dis.readInt()) {
                        dos.writeInt(dis.readInt());
                        dos.writeUTF(dis.readUTF());
                        dos.writeDouble(dis.readDouble());
                    }
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin archivo");
        }
        if (flag) {
            if (archivoPpal.delete()) {
                archivoTemp.renameTo(archivoPpal);
            }
        } else {
            archivoTemp.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("alumnos.dat"));
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
                    altaAlumnos("alumnos.dat", listaAlumnos);
                    break;
                case 2:
                    // Código opcion 2
                    consultarAlumnos(listaAlumnos);
                    break;
                case 3:
                    // Código opcion 3
                    modificarAlumnos(7, "alumnos.dat");
                    break;
                case 4:
                    // Código opcion 4
                    eliminarAlumnos(7, "alumnos.dat");
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
}
