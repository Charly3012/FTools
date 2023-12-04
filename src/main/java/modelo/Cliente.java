package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstracción de clientes finales
 */
public class Cliente implements Serializable {

    public String nombre;
    public long celular;
    public String genero;

    public Cliente(String nombre, long telefono, String genero) {
        this.nombre = nombre;
        this.celular = telefono;
        this.genero = genero;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return celular == cliente.celular && Objects.equals(nombre, cliente.nombre) && Objects.equals(genero, cliente.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, celular, genero);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + celular +
                ", genero='" + genero + '\'' +
                '}';
    }
}
