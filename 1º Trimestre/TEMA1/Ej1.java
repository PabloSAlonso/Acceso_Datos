import java.io.File;

public class Ej1 {
    public static void main(String[] args) {
        File carpeta = new File("hola");
        File archivos[] = carpeta.listFiles();

        for (int i = 0; i < archivos.length; i++) {
            if (archivos[i].isFile()) {
                System.out.println("Nombre archivo: + " + archivos[i].getName());
            }
        }
        for (int i = 0; i < archivos.length; i++) {
            if (archivos[i].isDirectory()) {
                System.out.println("Nombre directorio: " + archivos[i].getName());
            }
        }
    }
}
