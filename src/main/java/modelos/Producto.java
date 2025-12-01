package modelos;

import java.io.Serializable; // Importante: Permite convertir el objeto en datos guardables

/**
 * Clase Producto (Modelo)
 * Representa un artículo del inventario.
 * Implementa 'Serializable' para poder guardarse en un archivo .dat
 */
public class Producto implements Serializable {
    
    // Versión de la clase (para que Java no se confunda si cambias el código luego)
    private static final long serialVersionUID = 1L;

    // --- ATRIBUTOS (Encapsulamiento: son privados) ---
    private String codigo;
    private String nombre;
    private double precioCompra; // Vital para Contabilidad (Costos)
    private double precioVenta;  // Vital para Ventas (Ingresos)
    private int stock;

    // --- CONSTRUCTOR (Para crear nuevos productos) ---
    public Producto(String codigo, String nombre, double precioCompra, double precioVenta, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    // --- GETTERS Y SETTERS (Para leer y modificar datos) ---
    // Son necesarios para que la Tabla visual pueda mostrar la info

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Método para Contabilidad:
     * Calcula cuánto dinero tienes invertido en este lote de productos.
     */
    public double calcularValorInventario() {
        return this.precioCompra * this.stock;
    }
}
