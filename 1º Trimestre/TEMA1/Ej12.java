import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ej12 {
    public static void main(String[] args) {
        // ESCRIBIR OBJETOS
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Denis", 5, 100.0F));
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Lugonpa", 21));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
            if (fichero.length() == 0)
                out = new ObjectOutputStream(fos);
            else
                out = new AppendingObjectOutputStream(fos);
            for (Cliente cliente : clientes) {
                out.writeObject(cliente);
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
