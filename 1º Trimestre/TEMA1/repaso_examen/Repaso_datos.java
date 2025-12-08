import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Repaso_datos {
    public static void aTexto(String fichero_binario, String fichero_texto) {
        try (FileInputStream fis = new FileInputStream(fichero_binario);
                DataInputStream dis = new DataInputStream(fis);
                FileWriter fw = new FileWriter(fichero_texto)) {
            for (int i = 0; i < 4; i++) {
                dis.readUTF();// leer cabecera, 4 de condicion pq habia 4 elementos
            }
            int año = 0;
            float valoracion = 0.0f;
            String titulo = "";
            String autor = "";
            while (true) {
                año = dis.readInt();
                fw.write(año + " ");
                valoracion = dis.readFloat();
                fw.write(valoracion + " ");
                titulo = dis.readUTF();
                fw.write(titulo + " ");
                autor = dis.readUTF();
                fw.write(autor + " ");
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
    }

    // Ficheros (1.30 pt). Crea el método reemplaza que dado dos ficheros y dos
    // cadenas sustituya todas las apariciones, en el primer fichero, de la primera
    // cadena por la segunda. El resultado se debe almacenar en el segundo fichero.
    // No
    // se pueden utilizar estructuras en memoria tipo arrays, arrayList, hashMaps, …
    // y
    // no hay que realizar lecturas a disco incensarías.
    public static void reemplaza(String fichero_origen, String fichero_destino, String cadena_original,
            String cadena_destino) {
        try (Scanner sc = new Scanner(new File(fichero_origen)); FileWriter fw = new FileWriter(fichero_destino)) {
            String linea = "";
            while (sc.hasNext()) {
                linea = sc.nextLine().replace(cadena_original, cadena_destino);
                fw.write(linea);
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
    }

    // Crea un método que permita eliminar todas las ocurrencias de una palabra dada
    // en un fichero de texto. Este código producirá automáticamente un fichero con
    // la
    // siguiente nomenclatura: Si el fichero de entrada se llama fichero.txt, el
    // fichero generado se llamará fichero_2.txt.
    public static void ejercicio_extra_4(String fichero_entrada, String palabra_borrar) {
        try (Scanner sc = new Scanner(new File(fichero_entrada))) {
            String[] titulo_en_array = fichero_entrada.split(".txt");
            String titulo_nuevo = "";
            titulo_nuevo = titulo_en_array[0] + "_3.txt";
            FileWriter fw = new FileWriter(titulo_nuevo);
            // String linea = "";
            while (sc.hasNext()) {
                fw.write(sc.nextLine().replaceAll(palabra_borrar, ""));
                // fw.write(linea); se puede hacer o no
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IAGO BOCON");
        }
    }

    // Lee libros.dat como fichero de bytes y escribe
    // un objeto libro en un txt hecho a partir de los datos de libros.dat
    public static void crear_objetos_libro(String fichero_binario, String fichero_texto) {
        try (FileInputStream fis = new FileInputStream(fichero_binario);
                DataInputStream dis = new DataInputStream(fis);
                FileWriter fw = new FileWriter(fichero_texto)) {
                    dis.readUTF();
                    dis.readUTF();
                    dis.readUTF();
                    dis.readUTF();
                    int año = 0;
                    float valoracion = 0.0f;
                    String autor = "";
                    String titulo = "";
                    Libro nuevo_libro;
                    while (true) {
                        año = dis.readInt();
                        valoracion = dis.readFloat();
                        titulo = dis.readUTF();
                        autor = dis.readUTF();
                        nuevo_libro = new Libro(año, valoracion, titulo, autor);
                        fw.write(nuevo_libro.toString());
                        fw.write("\n");
                    }
        } catch (IOException e) {
            System.out.println("Monti bocón");
        }
    }

    public static void main(String[] args) {
        // aTexto("repaso_examen\\libros.dat", "repaso_examen\\libros.txt");
        // reemplaza("repaso_examen\\dos.txt", "repaso_examen\\nuevo_dos.txt", "celta",
        // "Ourense");
        // ejercicio_extra_4("repaso_examen\\archivo_ejemplo.txt", "costa");
        // crear_objetos_libro("repaso_examen\\libros.dat", "repaso_examen\\objetos_libro.txt");
    }
}
// Ficheros (1.70 pt). Crea el método aTexto que permita transformar un fichero
// binario que contiene objetos de tipo libro en un fichero texto. La primera
// línea del
// fichero binario es una cabecera con los nombres de los datos que se van a
// escribir.
// En el fichero de texto generado:
// Cada libro ocupara una única línea en el fichero de texto.
// Se verá la cabecera con el nombre de los datos
// Los datos de cada objeto libro son:
// Donde:
// DATA:
// 19/11/2025
// El nombre del fichero binario será el mismo que el del fichero origen pero
// con
// extensión txt almacenándose en su mismo directorio.
// Si el fichero de origen no existe o no contiene datos el fichero generado
// estará vacio
// Como fuente de datos de pruebas se puede usar el fichero libros.dat.
