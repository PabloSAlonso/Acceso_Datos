import java.io.File;

public class claseFile {
    public static void main(String[] args) {
        File carpeta = new File("hola");
        File archivos[] = carpeta.listFiles();
        // for (int i = 0; i < archivos.length; i++) {
        //     System.out.println(archivos[i].getName());
        // }
        carpeta.getName(); //Devuelve el nombre del archivo
        carpeta.length(); //Devuelve longitud en bytes del fichero
        carpeta.getPath(); //Devuelve la ruta relativa del archivo
        carpeta.getAbsolutePath(); //Devuelve la ruta relativa del archivo
        carpeta.exists(); //Booleano, true si existe, false sino
        carpeta.mkdir(); //Crea el directorio si no existe
        carpeta.renameTo(carpeta); //Cambia el nombre al  archivo
        carpeta.delete(); //borra el archivo
        carpeta.isDirectory(); //Booleano, true si es una carpeta
        carpeta.isFile(); //Booleano, true si es archivo


        // carpeta.listFiles(); //Lista en un array de objetos tipo File la lista de archivos de la carpeta
        
    }
}
