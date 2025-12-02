module com.mycompany.siaralite 
{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.siaralite to javafx.fxml;
    exports com.mycompany.siaralite;
}
