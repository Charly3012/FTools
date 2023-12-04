package modelo;

import java.io.Serializable;

public class DatosVenta extends Producto implements Serializable {

    private String cantidad;
    private double subtotal;



    public DatosVenta(String nombre, int cantExistencia, double precioUnitario, String cantidad, double subtotal) {
        super(nombre, cantExistencia, precioUnitario);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DatosVenta(String nombre, String cantidad, double subtotal) {
        super(nombre);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    public DatosVenta(String nombre, String cantidad, double subtotal, double precioUnitario, int cantExistencia) {
        super(nombre, precioUnitario, cantExistencia);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
