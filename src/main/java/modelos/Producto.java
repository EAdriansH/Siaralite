package modelos;

import java.io.Serializable; // Importante: Permite convertir el objeto en datos guardables

/**
 * Clase Producto (Modelo)
 * Representa un artículo del inventario.
 * Implementa 'Serializable' para poder guardarse en un archivo .dat
 * lo que hace es convertir en un flujo de bytes la informacion para su persistencia o transporte, 
 * y se puede deserializar para reconstruir el objeto a partir de ese flujo
 */
public class Producto implements Serializable 
{
    
    /* 
    Versión de la clase para evitar problemas futuros si se cambia alguna parte del codigo
    */
    private static final long serialVersionUID = 1L;

    /*Atributos privados para seguridad */
    private String codigo;
    private String nombre;
    private double precioCompra; // Vital para Contabilidad (Costos)
    private double precioVenta;  // Vital para Ventas (Ingresos)
    private int stock;

    //Constructor de los productos
    public Producto(String codigo, String nombre, double precioCompra, double precioVenta, int stock) 
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

   /**
    *Estos Getters y Setters permitiran que puedan obtener y asignar datos    
    *son funciones simples que para cuando se necesite en alguna otra clase u otro lado del programa funcionen sin problema.    
    */
    public String getCodigo() 
    { 
        return codigo; 
    }
    public void setCodigo(String codigo) 
    { 
        this.codigo = codigo; 
    }

    public String getNombre() 
    { 
        return nombre; 
    }
    public void setNombre(String nombre) 
    { 
        this.nombre = nombre; 
    }

    public double getPrecioCompra() 
    { 
        return precioCompra; 
    }
    public void setPrecioCompra(double precioCompra) 
    { 
        this.precioCompra = precioCompra; 
    }

    public double getPrecioVenta()
    {
        return precioVenta; 
    }
    public void setPrecioVenta(double precioVenta) 
    { 
        this.precioVenta = precioVenta; 
    }
    public int getStock() 
    { 
        return stock; 
    }
    public void setStock(int stock) 
    { 
        this.stock = stock; 
    }

    /**
     * Método para Contabilidad:
     * Calcula cuánto dinero hay invertido en el lote  de productos.
     * solo multiplica el precio con el que lo compramos con la cantidad de stock que tenemos.
     */
    public double calcularValorInventario() 
    {
        return (this.precioCompra * this.stock);
    }
}
