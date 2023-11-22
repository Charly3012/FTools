package modelo;

import java.util.Objects;

public class Proveedor {
    private String nombre;
    private String direccion;
    private String correo;
    private int numero;

    public Proveedor(String nombre, String direccion, String correo, int numero) {
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

    public void setCorreo(String coreo) {
        this.correo = correo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return numero == proveedor.numero && Objects.equals(nombre, proveedor.nombre) && Objects.equals(direccion, proveedor.direccion) && Objects.equals(correo, proveedor.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion, correo, numero);
    }
}
