package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditDeleteCatalogController {

    @FXML
    private TextField idEdit;
    @FXML
    private TextField catalogName;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;
    @FXML
    private TextField idDelete;
    @FXML
    private Label catalogLabel;
    // ID input default value
    private int inputID;
    // Database adapter
    private CatalogAdapter catalogAdapter;
    private ItemAdapter itemAdapter;

    public void setModel(CatalogAdapter catalogAdapter, ItemAdapter itemAdapter){
        this.catalogAdapter = catalogAdapter;
        this.itemAdapter = itemAdapter;
    }

    public void cancel() { // Close the window without saving
        Stage stage = (Stage)this.cancelButton.getScene().getWindow();
        stage.close();
    }

    public void searchCatalogEdit() throws SQLException {
        if(BackgroundApp.CheckInt(idEdit.getText())) {
            inputID = Integer.parseInt(idEdit.getText());
            Catalog searchedCatalog = catalogAdapter.searchCatalog(inputID);
            try {
                catalogName.setEditable(true);
                catalogName.setText(searchedCatalog.getCatalogName());
            } catch (NullPointerException e) {
                catalogName.setEditable(false);
                catalogName.setText("Catalog Not Found");
            }
        }
    }

    public void searchCatalogDelete() throws SQLException{
        if(BackgroundApp.CheckInt(idDelete.getText())) {
            inputID = Integer.parseInt(idDelete.getText());
            Catalog searchedCatalog = catalogAdapter.searchCatalog(inputID);
            try {
                catalogLabel.setText(searchedCatalog.getCatalogName());
            } catch (NullPointerException e) {
                catalogLabel.setText("Catalog Not Found");
            }
        }
    }

    public void onEditClick() throws SQLException {

        // Edit the Catalog to database
        if(BackgroundApp.CheckInt(idEdit.getText()) && catalogAdapter.searchCatalog(Integer.parseInt(idEdit.getText())) != null) {
            if(catalogName.getText().equals(""))
                BackgroundApp.displayAlert("Please Enter In A Name");

            else {
                try {
                    catalogAdapter.updateCatalog(Integer.parseInt(idEdit.getText()), catalogName.getText());
                    BackgroundApp.displayAlert("Catalog Edited!");
                } catch (SQLException e) {
                    BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on edit");
                }
            }
        }
    }

    public void onDeleteClick() throws SQLException{

        // Delete the Catalog using ID
        if(BackgroundApp.CheckInt(idDelete.getText())) {
            try {
                if (itemAdapter.checkForItems(inputID)) {
                    BackgroundApp.displayAlert("There Is Still An Item In This Catalog");
                } else {
                    catalogAdapter.deleteCatalog(Integer.parseInt(idDelete.getText()));
                    BackgroundApp.displayAlert("Catalog Deleted!");
                }
            } catch (SQLException e) {
                BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on delete");
            } catch (NullPointerException ex) {
                BackgroundApp.displayAlert("Catalog Not Found");
            }
        }
    }
}
