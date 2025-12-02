package logica;
import modelos.Producto;

import java.io.*; // Librería para manejo de archivos
import java.util.ArrayList;

/**
 * Clase GestorInventario
 * Se encarga de la persistencia (Guardar/Cargar) y cálculos generales.
 */
public class GestorInventario 
{

    /**
    *Nombre del archivo donde se guardarán los datos (se crea en la carpeta del proyecto)
    *
    */
    private final String ARCHIVO_BD = "inventario_siara.dat";
    
    /*
    esta es la lista en memoria RAM donde trabajaremos mientras la app está abierta
    se crea un objeto de tipo arraylist el cual recibe como parametro Producto el cual proviene de modelos
    */
    private ArrayList<Producto> listaProductos;

    public GestorInventario() 
    {
        // Al iniciar el gestor, intentamos cargar lo que haya en el archivo
        this.listaProductos = cargarDelArchivo();
    }

    /**
     * Agrega un producto a la lista y guarda cambios en el disco duro.
     */
    public void agregarProducto(Producto p) 
    {
        listaProductos.add(p);
        guardarEnArchivo(); // ¡Guardado automático!
    }

    /**
     * Método para la Expo de Contabilidad.
     * Suma el valor de todos los productos para saber el Activo total.
     */
    public double obtenerActivoTotal() 
    {
        double total = 0;
        for (Producto p : listaProductos) //for each para reconocer
        {
            total =total+p.calcularValorInventario();
        }
        return total;
    }

    public ArrayList<Producto> getListaProductos()
    {
        return listaProductos;
    }

    // --- MÉTODOS PRIVADOS DE ARCHIVOS (La parte técnica) ---

    // Escribe la lista en el archivo .dat
    private void guardarEnArchivo() {
        // Try-with-resources: Cierra el archivo automáticamente al terminar
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BD))) {
            oos.writeObject(listaProductos);
            System.out.println("Datos guardados en disco.");
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    // Lee la lista del archivo .dat
    private ArrayList<Producto> cargarDelArchivo()
    {
        ArrayList<Producto> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_BD);

        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BD))) {
                lista = (ArrayList<Producto>) ois.readObject();
                System.out.println("Datos recuperados exitosamente.");
            } catch (Exception e) {
                System.err.println("Error al cargar archivo: " + e.getMessage());
            }
        }
        return lista;
    }
}
