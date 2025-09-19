import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Ej13_2 {
    public static void main(String[] args) throws ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream("clientes.dat");
        ObjectInputStream ois = new ObjectInputStream(fin)) {

            Persona persona;
            
            while (true){
                Object object = ois.readObject();
                if (object.getClass() == Persona.class){
                    persona = (Persona) object;
                    System.out.println(persona.getNombre() + " - " + persona.getEdad());
                }
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
