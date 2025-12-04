package menuprincipal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.image.Image;

public class App extends Application
{
@Override
    public void start(Stage stage) throws Exception 
    {
    
        URL archivoFXML = getClass().getResource("/vista_principal.fxml");
        
        FXMLLoader loader = new FXMLLoader(archivoFXML);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("S.I.A.R.A. Lite - Abarrotes");
        Image icono = new Image(getClass().getResourceAsStream("/logo.png"));
            stage.getIcons().add(icono);
        stage.setScene(scene);
        stage.show();//esto muestra la ventana
    
    }
public static void main(String[] args) 
{
        launch(args);//esto hace arrancar a javafx
}

}
