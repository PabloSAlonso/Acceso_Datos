import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio10 {
    public static void pedirObjeto(ArrayList<Object> lista) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quieres añadir una persona o un departamento? (1/2)");
        int opcion = sc.nextInt();
        FileOutputStream fout = new FileOutputStream("cosas.dat",true);
        try (ObjectOutputStream objOut = new ObjectOutputStream(fout)){
            
        }
            
        if (opcion == 1){

        }
        lista.add(lista);
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
        switch (opcion) {
            case 1:
                // Código opcion 1
                pedirObjeto(listaObjetos);
                break;
            case 2:
                // Código opcion 2
                for (int i = 0; i > listaObjetos.size(); i++){
                    
                }
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
        } 	while (opcion != 4);
    }
}
