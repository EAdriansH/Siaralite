package controles;
import modelos.Producto;
import logica.GestorInventario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*; // Importa Botones, Labels, Tablas
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Clase MenuController
 * Controla la interacción entre la ventana visual (FXML) y la lógica Java.
 */
public class MenuController 
{
 // --- ELEMENTOS VISUALES (Conectados con SceneBuilder vía fx:id) ---
    
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioC; // Precio Compra
    @FXML private TextField txtPrecioV; // Precio Venta
    @FXML private TextField txtStock;
    
    @FXML private Label lblTotalDinero; // Etiqueta para mostrar el reporte contable

    @FXML private TableView<Producto> tablaProductos; // La tabla principal
    
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    // --- OBJETOS DE LÓGICA ---
    private GestorInventario gestor;
    // Lista especial de JavaFX que actualiza la tabla automáticamente
    private ObservableList<Producto> listaVisual; 

    /**
     * Método initialize
     * Se ejecuta AUTOMÁTICAMENTE al abrir la ventana.
     * Sirve para configurar cosas iniciales.
     */
    @FXML
    public void initialize() 
    {
        gestor = new GestorInventario(); // Iniciamos el cerebro
        
        // Configuramos las columnas de la tabla para que sepan qué dato mostrar
        // "nombre" debe coincidir con el atributo en Producto.java
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colStock.setCellValueFactory(new PropertyValueFactory<> ("stock"));

        // Cargamos los datos en la tabla
        actualizarTabla();
        actualizarReporteContable();
    }

    /**
     * Método vinculado al BOTÓN "GUARDAR" en SceneBuilder
     */
    @FXML
    private void clicGuardar() {
        try 
        {
            // 1. Obtenemos datos de las cajitas de texto
            String cod = txtCodigo.getText();
            String nom = txtNombre.getText();
            double preC = Double.parseDouble(txtPrecioC.getText());
            double preV = Double.parseDouble(txtPrecioV.getText());
            int stk = Integer.parseInt(txtStock.getText());

            // 2. Creamos el objeto
            Producto nuevo = new Producto(cod, nom, preC, preV, stk);

            // 3. Lo mandamos a la lógica
            gestor.agregarProducto(nuevo);

            // 4. Actualizamos la vista
            actualizarTabla();
            actualizarReporteContable();
            limpiarCampos();
            
            // Mensaje de éxito (Opcional: Usar Alert)
            System.out.println("✅ Producto registrado.");

        } 
        catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingresa números válidos en precio/stock");
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private void actualizarTabla() {
        // Convertimos la ArrayList normal a una ObservableList de JavaFX
        listaVisual = FXCollections.observableArrayList(gestor.getListaProductos());
        tablaProductos.setItems(listaVisual);
    }
    
    private void actualizarReporteContable() {
        double total = gestor.obtenerActivoTotal();
        // Formato de dinero
        lblTotalDinero.setText("Valor Inventario: $" + String.format("%.2f", total));
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecioC.clear();
        txtPrecioV.clear();
        txtStock.clear();
    }
}