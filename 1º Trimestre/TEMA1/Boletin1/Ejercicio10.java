import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio10 {
    /**
     * Funcion que devuelve una Persona
     * @param nombrePersona nombre de la persona
     * @param edad edad de la persona
     * @return Un objeto tipo persona
     */
    public static Persona crearPersona(String nombrePersona, int edad) {
        return new Persona(nombrePersona, edad);
    }
    /**
     * Funcion que devuelve un departamento
     * @param nombreDepart nomre del departamento
     * @param codigoDepart codigo del departamento
     * @return Un objeto tipo Departamento
     */
    public static Departamento crearDepartamento(String nombreDepart, int codigoDepart) {
        return new Departamento(nombreDepart, codigoDepart);
    }

    /**
     * Funcion que añade un objeto si este es de tipo Persona o departamento a un ArrayList pasado como parametro
     * @param listaObj El arrayList donde se añadirá el objeto
     * @param obj El objeto que queremos añadir
     */
    public static void añadirObjetos(ArrayList<Object> listaObj, Object obj) {
        if (obj.getClass() == Persona.class || obj.getClass() == Departamento.class) {
            listaObj.add(obj);
        }
    }

    /**
     * Funcion que escribe un archivo con objetos contenidos en una Coleccion tipo Object
     * @param fichero el nombre que tendrá el fichero con los objetos
     * @param listaObj la colección que pintará en el fichero
     * @throws FileNotFoundException
     */
    public static void escribirArchivo(File fichero, ArrayList<Object> listaObj) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(fichero);
        try (ObjectOutputStream out = new ObjectOutputStream(fos)) {
            for (Object obj : listaObj) {
                out.writeObject(obj);
                //Escribimos ccon foreach en el archivo cada elemento de la coleccion pasada como parametro
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    /**
     * Funcion que muestra el contenido de un fichero
     * @param fichero el fichero del que se mostrará el contenido
     * @return
     */
    public static ArrayList<Object> consultarArchivo(File fichero) {
        ArrayList<Object> listaObj = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                listaObj.add(ois.readObject());
            }
        } catch (Exception e) {
        }
        return listaObj;
        //Hacemos un arrayList nuevo con el contenido del archivo y lo devolvemos
        //para asi mostrar basicamente el contenido de este
    }
    /**
     * Funcion que elimina un objeto de una coleccion
     * @param objetos La coleccion de la que deseamos eliminar el objeto
     * @param obj El objeto que vamos a eliminar
     */
    public static void eliminarObjeto(ArrayList<Object> objetos, Object obj) {
        for (int i = objetos.size(); i > 0; i--) {
            if (obj.getClass() == Persona.class && objetos.get(i).getClass() == Persona.class) {
                if (((Persona) obj).getNombre().equals(((Persona) objetos.get(i)).getNombre())) {
                    objetos.remove(i);
                }
            } else if (obj instanceof Departamento && objetos.get(i) instanceof Departamento) {
                if (((Departamento) obj).getCodigo() == ((Departamento) objetos.get(i)).getCodigo()) {
                    objetos.remove(i);
                }
            } else {
                System.out.println("No existe ese objeto");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Object> listaObjetos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1.- Inclusion de nuevas personas o departamentos");
            System.out.println("2.- Mostrar datos archivo");
            System.out.println("3.- Eliminar Persona o departamento");
            System.out.println("4.- Salir");
            opcion = sc.nextInt();
            switch (opcion) { //TODO hacer main
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
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);
    }
}
