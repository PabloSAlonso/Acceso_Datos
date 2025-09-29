import java.io.Serializable;

public class Persona implements Serializable{
    public String nombre;
    public int edad;
    private int id;

    public Persona(String nombre, int edad, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }
    public Persona(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %10s, Edad %3d con ID: %3d", nombre, edad, id);
    }
}
