import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ej9 {

    public static void main(String[] args) {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("clientes.dat"))) {
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            listaClientes.add(new Cliente("Iago", 5, 100.3f));
            for (Cliente cliente : listaClientes) {
                dout.writeUTF(cliente.getNombre());
                dout.writeInt(cliente.getNumCompras());
                dout.writeFloat(cliente.getCredito());
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
