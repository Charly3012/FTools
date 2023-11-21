package modelo;

public class Proveedor {
    private String nombre;
    private String direccion;
    private String coreo;
    private int numero;

    public Proveedor(String nombre, String direccion, String coreo, int numero) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.coreo = coreo;
        this.numero = numero;
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

    public String getCoreo() {
        return coreo;
    }

    public void setCoreo(String coreo) {
        this.coreo = coreo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
