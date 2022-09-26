package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCatalogController {

    // FXML fields
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField ID;
    @FXML
    private TextField catalogName;
    private CatalogAdapter catalogAdapter;

    public void setModel(CatalogAdapter catalogAdapter){
        this.catalogAdapter = catalogAdapter;
    }

    public void cancel() { // Close the window without saving
        Stage stage = (Stage)this.cancelButton.getScene().getWindow();
        stage.close();
    }

    public void save() {

        // Add the catalog to database
        try {
            if(catalogName.getText().equals(""))
                BackgroundApp.displayAlert("Please Enter In A Name");
            else if(BackgroundApp.CheckInt(ID.getText())) {
                if(catalogAdapter.searchCatalog(Integer.parseInt(ID.getText())) == null) {
                    catalogAdapter.insertCatalog(Integer.parseInt(ID.getText()), catalogName.getText());
                    BackgroundApp.displayAlert("Catalog Added!");

                    ID.clear();
                    catalogName.clear();
                }
                else
                    BackgroundApp.displayAlert("Catalog Already Exists!");
            }
        }catch (SQLException e){
            BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on Save");
        }
    }


}
