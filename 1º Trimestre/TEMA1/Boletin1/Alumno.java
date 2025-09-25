public class Alumno {

    private int codigo;
    private String nombre;
    private float altura;

    public Alumno(int codigo, String nombre, float altura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.altura = altura;
    }
    public Alumno(){
        this(0,"",0.0f);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public float getAltura() {
        return altura;
    }
}
