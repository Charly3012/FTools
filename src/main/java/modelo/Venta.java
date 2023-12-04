package modelo;

import java.util.ArrayList;

public class Venta{

    private long cliente;
    private double total;
    private String fecha;
    private ArrayList<DatosVenta> detalleVenta;

    public Venta(long cliente, double total, String fecha, ArrayList<DatosVenta> detalleVenta) {
        this.cliente = cliente;
        this.total = total;
        this.fecha = fecha;
        this.detalleVenta = detalleVenta;
    }

    public Venta() {
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DatosVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(ArrayList<DatosVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }


}
