public class Libro {
    int año_publicacion;
    float valoracion;
    String titulo;
    String autor;

    public Libro(int año_publicacion, float valoracion, String titulo, String autor) {
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.autor = autor;
        this.valoracion = valoracion;
    }

    public String getAutor() {
        return autor;
    }

    public int getAño_publicacion() {
        return año_publicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {

        return String.format("Año: %d, Valoracion: %f, Autor: %s, Titulo: %s", año_publicacion, valoracion, autor,
                titulo);
    }
}
