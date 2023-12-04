package modelo;

import java.io.Serializable;

public class DatosVenta extends Producto implements Serializable {

    private int cantidad;
    private double subtotal;

    public DatosVenta(String nombre, String marca, String sku, int codigoBarras, int cantExistencia, double precioUnitario, String categoria, String descripcion, int cantidad, double subtotal) {
        super(nombre, marca, sku, codigoBarras, cantExistencia, precioUnitario, categoria, descripcion);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DatosVenta(String nombre, String categoria, int cantExistencia, String sku, int cantidad, double subtotal) {
        super(nombre, categoria, cantExistencia, sku);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DatosVenta(String nombre, int cantExistencia, double precioUnitario, int cantidad, double subtotal) {
        super(nombre, cantExistencia, precioUnitario);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DatosVenta(String nombre, int cantidad, double subtotal) {
        super(nombre);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


}
