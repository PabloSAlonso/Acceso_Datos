import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ej11 {
    public static void main(String[] args) throws IOException {
        Cliente c1 = new Cliente("UNO", 1, 10);
        Cliente c2 = new Cliente("DOS", 2, 20);
        Cliente c3 = new Cliente("TRES", 3, 30);
        Cliente c4 = new Cliente("CUATRO", 4, 40);
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("clientes.dat"));
                DataInputStream din = new DataInputStream(new FileInputStream("clientes.dat"))) {
            ArrayList<Cliente> clientes = new ArrayList<>();
            clientes.add(c1);
            clientes.add(c2);
            clientes.add(c3);
            clientes.add(c4);
            for (Cliente cliente : clientes) {
                dout.writeUTF(cliente.getNombre());
                dout.writeInt(cliente.getNumCompras());
                dout.writeDouble(cliente.getCredito());
            }
            while (true) {
                System.out.printf("Nombre: %s, Numcompras: %d, Crédito: %f\n", din.readUTF(), din.readInt(),
                        din.readFloat());
            }
        } catch (EOFException e) {
            System.out.println("FIN DE ARCHIVO");
        }
    }
}
