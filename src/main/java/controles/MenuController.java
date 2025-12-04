package controles;
import modelos.Producto;
import logica.GestorInventario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*; // Importa Botones, Labels, Tablas
import javafx.scene.control.cell.PropertyValueFactory;

/*
 *Clase MenuController 
 *Controla la interacción entre la ventana visual (FXML) y la lógica Java.
 * 
 */

public class MenuController 
{
/*
  * Antes de agregarlo se paso la clase MenuController al apartado de controller class que permite asociar una clase al scenebuilder    
  * ELEMENTOS VISUALES (Conectados con SceneBuilder vía fx:id) 
  * son variables de tipo privado los cuales tienen como nombre para identificacion sencilla txtNombre
  * ya que son los nombres que recibira el scenebuilder
  * en [SCENEBUILDER]: en el apartado de code hay un identificador que dice asi fx:id (txtNombre)
  *
  */
    
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioC; // Precio Compra
    @FXML private TextField txtPrecioV; // Precio Venta
    @FXML private TextField txtStock;
    
    @FXML private Label lblTotalDinero; // Etiqueta para mostrar el reporte contable
    /*
    en si esto es una tabla que recibe de parametro a Producto es como una asociacion
    */
    @FXML private TableView<Producto> tablaProductos; // La tabla principal
    /*si deseamos agregar otra columna a la tabla necesitamos agregar un nombre de identificacion*/
    @FXML private TableColumn<Producto, String> colcodigo;
    @FXML private TableColumn<Producto, String> colNombre;//columnaNombre
    @FXML private TableColumn<Producto, Double> colPrecioVenta;
    @FXML private TableColumn<Producto, Double> colPrecioCompra;
    @FXML private TableColumn<Producto, Integer> colStock;
    
    /*
    *OBJETOS DE LÓGICA 
    */
    //objeto gestor el cual hace que se inicialize el gestor de inventario
    private GestorInventario gestor;
    
    // Lista especial de JavaFX que actualiza la tabla automáticamente
    private ObservableList<Producto> listaVisual; 

    /**
     * Método initialize
     * Se ejecuta AUTOMÁTICAMENTE al abrir la ventana.
     * Sirve para configurar cosas iniciales.
     */
@FXML//esto hace que la funcion o variable se asocie con el scenebuilder
    public void initialize() 
    {
        gestor = new GestorInventario(); // Iniciamos el cerebro
        
        // Configuramos las columnas de la tabla para que sepan qué dato mostrar
        // "nombre" debe coincidir con el atributo en Producto.java para tomar sus datos
        colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
        colStock.setCellValueFactory(new PropertyValueFactory<> ("stock"));
        
        // Cargamos los datos en la tabla
        actualizarTabla();
        actualizarReporteContable();
    }

    /**
     * Método vinculado al BOTÓN "GUARDAR" en SceneBuilder
     */
@FXML
    private void clicGuardar() 
    {
        try 
        {
            // 1. Obtenemos datos de las cajitas de texto
            String cod = txtCodigo.getText();
            String nom = txtNombre.getText();
            double preC = Double.parseDouble(txtPrecioC.getText());
            double preV = Double.parseDouble(txtPrecioV.getText());
            int stk = Integer.parseInt(txtStock.getText());

            // 2. Creamos el objeto de forma ordenada para que lo entienda el constructor
            Producto nuevo = new Producto(cod, nom, preC, preV, stk);

            // 3. Lo mandamos a la lógica
            gestor.agregarProducto(nuevo);

            // 4. Actualizamos la vista
            actualizarTabla();
            actualizarReporteContable();
            limpiarCampos();
            
            // Mensaje de éxito (falta adaptar estos mensajes con un JOptionPane)
            System.out.println("Producto registrado.");

        } 
        catch (NumberFormatException e) 
        {
            //adaptar estos mensajes a joptionpane
            System.out.println("Error: Ingresa numeros válidos en precio/stock");
        }
    }
@FXML
    private void clicEliminar() {
        // 1. Preguntamos a la tabla: "¿Qué renglón seleccionó el usuario?"
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();

        // 2. Validación de seguridad (Por si dan clic sin seleccionar nada)
        if (seleccionado == null) 
        {
            System.out.println("¡Oye! Selecciona un producto primero.");
            // (Opcional: Aquí podrías poner una Alerta visual)
            return; // Se sale del método y no hace nada más
        }

        // 3. Llamamos al Gestor para que lo borre de verdad
        gestor.eliminarProducto(seleccionado);

        // 4. Actualizamos la pantalla (Refrescamos la tabla y el dinero total)
        actualizarTabla();
        actualizarReporteContable();
        
        // 5. Mensaje de éxito
        System.out.println("Adios producto.");
    }
    @FXML
    private void clicActualizar() 
    {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        
        if (p == null) 
        {
            System.out.println("Selecciona algo para editar.");
            return;
        }

        try 
        {
            // 1. Le metemos los nuevos valores de las cajitas al objeto
            p.setCodigo(txtCodigo.getText());
            p.setNombre(txtNombre.getText());
            p.setPrecioCompra(Double.parseDouble(txtPrecioC.getText()));
            p.setPrecioVenta(Double.parseDouble(txtPrecioV.getText()));
            p.setStock(Integer.parseInt(txtStock.getText()));

            // 2. Guardamos en el archivo
            gestor.guardarCambios();

            // 3. Refrescamos la tabla para que se vea el cambio
            tablaProductos.refresh(); 
            actualizarReporteContable(); // Recalcular dinero
            
            limpiarCampos(); // Borramos cajitas
            System.out.println("Producto actualizado.");

        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Revisa que los numeros sean correctos.");
        }
    }
    // --- MÉTODOS AUXILIARES ---
@FXML
    private void llenarCamposParaEditar() 
    {
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        
        if (p != null) 
        {
            txtCodigo.setText(p.getCodigo());
            txtNombre.setText(p.getNombre());
            // Truco: Convertimos números a texto con String.valueOf
            txtPrecioC.setText(String.valueOf(p.getPrecioCompra()));
            txtPrecioV.setText(String.valueOf(p.getPrecioVenta()));
            txtStock.setText(String.valueOf(p.getStock()));
        }
    }
    
    private void actualizarTabla() 
    {
        // Convertimos la ArrayList normal a una ObservableList de JavaFX
        listaVisual = FXCollections.observableArrayList(gestor.getListaProductos());
        tablaProductos.setItems(listaVisual);
    }
    
    private void actualizarReporteContable() 
    {
        double total = gestor.obtenerActivoTotal();
        // Formato de dinero
        lblTotalDinero.setText("Valor Inventario: $" + total);
    }

    private void limpiarCampos() 
    {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecioC.clear();
        txtPrecioV.clear();
        txtStock.clear();
    }
}