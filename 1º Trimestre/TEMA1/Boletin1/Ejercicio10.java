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
     * 
     * @param nombrePersona nombre de la persona
     * @param edad          edad de la persona
     * @return Un objeto tipo persona
     */
    public static Persona crearPersona(String nombrePersona, int edad, int id) {
        return new Persona(nombrePersona, edad, id);
    }

    /**
     * Funcion que devuelve un departamento
     * 
     * @param nombreDepart nomre del departamento
     * @param codigoDepart codigo del departamento
     * @return Un objeto tipo Departamento
     */
    public static Departamento crearDepartamento(String nombreDepart, int codigoDepart) {
        return new Departamento(nombreDepart, codigoDepart);
    }

    /**
     * Funcion que añade un objeto si este es de tipo Persona o departamento a un
     * ArrayList pasado como parametro
     * 
     * @param listaObj El arrayList donde se añadirá el objeto
     * @param obj      El objeto que queremos añadir
     */
    public static void añadirObjetos(ArrayList<Object> listaObj, Object obj) {
        if (obj.getClass() == Persona.class || obj.getClass() == Departamento.class) {
            listaObj.add(obj);
        }
    }

    /**
     * Funcion que escribe un archivo con objetos contenidos en una Coleccion tipo
     * Object
     * 
     * @param fichero  el nombre que tendrá el fichero con los objetos
     * @param listaObj la colección que pintará en el fichero
     * @throws FileNotFoundException
     */
    public static void escribirArchivo(File fichero, ArrayList<Object> listaObj) throws FileNotFoundException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichero))) {
            out.writeObject(listaObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion que muestra el contenido de un fichero
     * 
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
        // Hacemos un arrayList nuevo con el contenido del archivo y lo devolvemos
        // para asi mostrar basicamente el contenido de este
    }

    /**
     * Funcion que elimina un objeto de una coleccion
     * 
     * @param objetos La coleccion de la que deseamos eliminar el objeto
     * @param obj     El objeto que vamos a eliminar
     */
    public static void eliminarObjeto(ArrayList<Object> objetos, Object obj) {
        for (int i = objetos.size() - 1; i >= 0; i--) {
            if (obj instanceof Persona && objetos.get(i) instanceof Persona) {
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
        File fichero = new File("cosas.dat");
        int respuesta = 0;
        int i = 0;
        do {
            System.out.println("1.- Inclusion de nuevas personas o departamentos");
            System.out.println("2.- Mostrar datos archivo");
            System.out.println("3.- Eliminar Persona o departamento");
            System.out.println("4.- Salir");
            opcion = sc.nextInt();
            switch (opcion) { // TODO hacer main
                case 1:
                    // Código opcion 1
                    System.out.println("Que deseas crear persona o departamento? (1/2)");
                    respuesta = sc.nextInt();
                    sc.nextLine();
                    if (respuesta == 1) {
                        System.out.println("Dime nombre y edad de tu nueva persona");
                        añadirObjetos(listaObjetos, crearPersona(sc.nextLine(), sc.nextInt(), i));
                        i++;
                    } else {
                        System.out.println("Dime nombre y codigo de tu nuevo departamento");
                        añadirObjetos(listaObjetos, crearDepartamento(sc.nextLine(), sc.nextInt()));
                        sc.nextLine();
                    }
                    escribirArchivo(fichero, listaObjetos);
                    break;
                case 2:
                    // Código opcion 2
                    listaObjetos = consultarArchivo(fichero);
                    System.out.println(consultarArchivo(fichero));
                    break;
                case 3:
                    // Código opcion 3
                    // listaObjetos = consultarArchivo(fichero);
                    System.out.println("Que deseas eliminar persona o departamento? (1/2)");
                    respuesta = sc.nextInt();
                    sc.nextLine();
                    if (respuesta == 1) {
                        System.out.println("Dime nombre de la persona que vas a eliminar");
                        eliminarObjeto(listaObjetos, new Persona(sc.nextLine()));

                    } else {
                        System.out.println("Dime el codigo del departamento a eliminar");
                        eliminarObjeto(listaObjetos, new Departamento(sc.nextInt()));
                    }
                    escribirArchivo(fichero, listaObjetos);
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
