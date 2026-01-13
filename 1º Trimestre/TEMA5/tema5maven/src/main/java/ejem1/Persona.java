package ejem1;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona implements Serializable {
    private int id;
    private String nombre;
    private boolean casado;
    private String sexo;

    public Persona() {

    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean getCasado() {
        return casado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

}
