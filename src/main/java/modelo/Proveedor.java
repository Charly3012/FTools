package modelo;

import java.util.List;
import java.util.Objects;

public class Proveedor {
    private String nombre;
    private String direccion;
    private String correo;
    private String numero;

    public Proveedor(String nombre, String direccion, String correo, String numero) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.numero = numero;
    }

    public Proveedor() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proveedor proveedor)) return false;
        return Objects.equals(getNombre(), proveedor.getNombre()) && Objects.equals(getDireccion(), proveedor.getDireccion()) && Objects.equals(getCorreo(), proveedor.getCorreo()) && Objects.equals(getNumero(), proveedor.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getDireccion(), getCorreo(), getNumero());
    }
}
