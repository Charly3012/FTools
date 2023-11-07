package controlador;

import java.util.Objects;

/**
 * Clase principal de la abstracción de los productos que se manejen en la tienda
 * @author Charly
 * @version 1.0
 */
public class Producto {
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
    private int codigoBarras;
    /**
     * Cantidad en existencia del producto para la venta
     */
    private int cantExistencia;
    /**
     * Precio unitario de cada producto, es el precio final de venta
     */
    private double precioUnitario;

    /**
     * Categoría a la que pertenece al producto
     */
    private String categoria;
    /**
     * Descripción corta y relevante para el producto
     */
    private String descripcion;

    /**
     * Constructor completo para crear un nuevo producto
     * @param nombre
     * @param marca
     * @param sku
     * @param codigoBarras
     * @param cantExistencia
     * @param precioUnitario
     * @param categoria
     * @param descripcion
     */
    public Producto(String nombre, String marca, String sku, int codigoBarras, int cantExistencia, double precioUnitario, String categoria, String descripcion) {
        this.nombre = nombre;
        this.marca = marca;
        this.sku = sku;
        this.codigoBarras = codigoBarras;
        this.cantExistencia = cantExistencia;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public Producto() {
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

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getCantExistencia() {
        return cantExistencia;
    }

    public void setCantExistencia(int cantExistencia) {
        this.cantExistencia = cantExistencia;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Función para comparar existencia en base de datos, no permite que existan dos productos con los mismos atributos
     * @param o objeto a comprar
     * @return booleano que determina la existencia o inexistencia de un producto igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return codigoBarras == producto.codigoBarras && cantExistencia == producto.cantExistencia && Double.compare(precioUnitario, producto.precioUnitario) == 0 && Objects.equals(nombre, producto.nombre) && Objects.equals(marca, producto.marca) && Objects.equals(sku, producto.sku) && Objects.equals(categoria, producto.categoria) && Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
