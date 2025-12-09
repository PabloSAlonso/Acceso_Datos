
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    // Crear el archivo con productos
    public static void escribir_objetos(String fichero_escrito) {
        try (FileOutputStream fos = new FileOutputStream(fichero_escrito);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new Producto(1, "Producto_uno", 20.0));
            ;
            oos.writeObject(new Producto(2, "Producto_dos", 40.0));
            ;
            oos.writeObject(new Producto(3, "Producto_tres", 60.0));
            ;
            oos.writeObject(new Producto(4, "Producto_cuatro", 80.0));
            ;
        } catch (IOException e) {
            System.out.println("ERROR ARCHIVO");
        }
    }

    // Leer un fichero .dat que contiene objetos de tipo Objeto y escribirlos
    // en un nuevo fichero de texto
    public static void leer_objetos(String fichero_binario, String fichero_texto) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fichero_binario);
                ObjectInputStream ois = new ObjectInputStream(fis);
                FileWriter fw = new FileWriter(fichero_texto)) {
            Producto nuevo_producto;
            while (true) {
                nuevo_producto = (Producto) ois.readObject();
                fw.write(nuevo_producto.toString());
                fw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("IAGO BOCON");
        }
    }

    // Lee un archivo caracter a caracter e indica el numero de ocurrencias en un
    // archivo nuevo de un
    // caracter pasado como parámetro
    public static void leer_ocurrencias(String fichero_leer, String fichero_escribir, String caracter_contar) {
        try (FileReader fr = new FileReader(fichero_leer); FileWriter fw = new FileWriter(fichero_escribir)) {
            int contador = 0;
            int i;
            String caracter_actual = "";
            char[] buffer = new char[1];
            while ((i = fr.read(buffer)) != -1) {
                caracter_actual = new String(buffer, 0, i);
                System.out.println(caracter_actual);
                if (caracter_actual.equals(caracter_contar)) {
                    contador++;
                }
            }
            fw.write(String.format("Numero de ocurrencias del caracter %s: %d", caracter_contar, contador));
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
    }

    public static void leer_ocurrencias_cadenas(String archivo, String palabra) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            int contador = 0;
            String palabra_actual = "";
            while (sc.hasNext()) {
                palabra_actual = sc.next();
                if (palabra_actual.equals(palabra)) {
                    contador++;
                }
            }
            System.out.println("La palabra ha aparecido:" + contador);
        } catch (IOException e) {
            System.out.println("Error de archivo");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // aTexto("repaso_examen\\libros.dat", "repaso_examen\\libros.txt");
        // reemplaza("repaso_examen\\dos.txt", "repaso_examen\\nuevo_dos.txt", "celta",
        // "Ourense");
        // ejercicio_extra_4("repaso_examen\\archivo_ejemplo.txt", "costa");
        // crear_objetos_libro("repaso_examen\\libros.dat",
        // "repaso_examen\\objetos_libro.txt");
        // escribir_objetos("repaso_examen\\objetos.dat");
        // leer_objetos("repaso_examen\\objetos.dat", "repaso_examen\\objetos.txt");
        leer_ocurrencias("repaso_examen\\ejemplo_caracteres.txt", "repaso_examen\\ejemplo_caracteres_solucion.txt",
                "a");
                leer_ocurrencias_cadenas("libros.txt", "xD");
    }
}
