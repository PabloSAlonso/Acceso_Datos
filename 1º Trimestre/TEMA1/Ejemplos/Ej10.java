import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ej10 {
    public static void main(String[] args) throws IOException {
        try (DataInputStream din = new DataInputStream(new FileInputStream("clientes.dat"))) {
            while (true) {
                System.out.println("Nombre: " + din.readUTF() + "\nNum Compras: " + din.readInt() + "\nuCredito: " + din.readFloat());
            }
        } catch (EOFException e) {
            System.out.println("FIN FICHERO");
        }
    }
}
