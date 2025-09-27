import java.util.ArrayList;

public class Ejercicio10 {
    
    public String toString(Object obj) {
        if (obj.getClass() == Persona.class){
            return String.format("Nombre de la Persona: %10s con edad %2d ", ((Persona)obj).nombre, ((Persona)obj).edad);
        } 
        return String.format("Nombre de departamento: %10s con codigo %2d ", ((Departamento)obj).nombre, ((Departamento)obj).codigo);
    }
    public static void main(String[] args) {
        ArrayList<Object> listaObjetos = new ArrayList<>();
        
    }
}
