package menuprincipal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
public class App extends Application
{
@Override
    public void start(Stage stage) throws Exception 
    {
    /*
        // 1. Buscamos el archivo con la ruta ABSOLUTA (la barra / es vital)
       
        
        // 2. PRUEBA DE SEGURIDAD esto lo que hace es buscar al archivo fxml y si lo encuentra muestra un mensaje en consola de que si ono 
        if (archivoFXML == null) 
        {
            System.out.println("ERROR FATAL: No encuentro el archivo /vista_principal.fxml");
            System.out.println("Verifica que esté en src/main/resources (no en subcarpetas)");
            // Detenemos aquí para que no explote feo
            return;
        } 
        else
        {
            System.out.println("Archivo encontrado en: " + archivoFXML);
        }
      */
        /*direccion del archivo FXML llamado vista principal el cual es la esencia 
        *es lo que editamos en el scenebuilder
        */
        URL archivoFXML = getClass().getResource("/vista_principal.fxml");
        
        FXMLLoader loader = new FXMLLoader(archivoFXML);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("S.I.A.R.A. Lite - Abarrotes");
        stage.setScene(scene);
        stage.show();//esto muestra la ventana
    
    }
public static void main(String[] args) 
{
        launch(args);//esto hace arrancar a javafx
}

}
