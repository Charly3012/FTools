package modelo;

import java.io.Serializable;
import java.util.Objects;

public class ProductoProveedor{
    /**
     * Nombre del producto
     */
    private String nombre;
    /**
     * Marca del producto
     */
    private String marca;
    /**
     * Codigo SKU único de cada producto
     */
    private String sku;
    /**
     * Código de barras del producto
     */
    private String codigoBarras;
    /**
     * Cantidad en existencia del producto para la venta
     */
    private String cantExistencia;
    /**
     * Precio unitario de cada producto, es el precio final de venta
     */
    private String precioUnitario;

    /**
     * Descripción corta y relevante para el producto
     */
    private String descripcion;

    private String nombreProveedor;

    public ProductoProveedor(String nombre, String marca, String sku, String codigoBarras, String cantExistencia, String precioUnitario, String descripcion, String nombreProveedor) {
        this.nombre = nombre;
        this.marca = marca;
        this.sku = sku;
        this.codigoBarras = codigoBarras;
        this.cantExistencia = cantExistencia;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
        this.nombreProveedor = nombreProveedor;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCantExistencia() {
        return cantExistencia;
    }

    public void setCantExistencia(String cantExistencia) {
        this.cantExistencia = cantExistencia;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoProveedor that)) return false;
        return Objects.equals(getNombre(), that.getNombre()) && Objects.equals(getMarca(), that.getMarca()) && Objects.equals(getSku(), that.getSku()) && Objects.equals(getCodigoBarras(), that.getCodigoBarras()) && Objects.equals(getCantExistencia(), that.getCantExistencia()) && Objects.equals(getPrecioUnitario(), that.getPrecioUnitario()) && Objects.equals(getDescripcion(), that.getDescripcion()) && Objects.equals(getNombreProveedor(), that.getNombreProveedor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getMarca(), getSku(), getCodigoBarras(), getCantExistencia(), getPrecioUnitario(), getDescripcion(), getNombreProveedor());
    }
}
