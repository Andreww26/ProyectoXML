package _5.Backend.Modelo;

import java.util.Objects;

public class Entrenamiento {
    private int id;
    private String nombre;
    private int duracion;
    private String nivel;

    public Entrenamiento(int id, String nombre, int duracion, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrenamiento that)) return false;
        return getId() == that.getId() && Objects.equals(getNombre(), that.getNombre()) && Objects.equals(getDuracion(), that.getDuracion()) && Objects.equals(getNivel(), that.getNivel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getDuracion(), getNivel());
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion='" + duracion + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
