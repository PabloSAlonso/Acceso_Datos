import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ej12 {
    public static void main(String[] args) throws IOException {
        // ESCRIBIR OBJETOS
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Denis", 5, 100.0F));
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Lugonpa", 21));
        File fichero = new File("clientes.dat");
        escribirObjetos(fichero,clientes);
        // escribirObjetos(fichero, personas);
    }
    public static void escribirObjetos(File fichero, ArrayList<Cliente> clientes){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fichero);
            if (fichero.length() == 0) out = new ObjectOutputStream(fos);
            else out = new AppendingObjectOutputStream(fos);

            for (Cliente cliente : clientes) {
                out.writeObject(cliente);
            }

            fos.close();
            out.close();

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
