import java.io.Serializable;

public class Departamento implements Serializable{
    public String nombre;
    public int codigo;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public Departamento(String nombre, int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public Departamento(int codigo){
        this.codigo = codigo;
    }
    public String toString() {
        return String.format("Nombre: %10s, Codigo: %3d", nombre, codigo);
    }

}
