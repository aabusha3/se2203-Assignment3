package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    // FXML fields
    @FXML
    private Button cancelButton;
    @FXML
    private TextField name;
    @FXML
    private TextField size;
    @FXML
    private TextField price;
    @FXML
    private TextField numberInStock;
    @FXML
    private TextField ID;
    @FXML
    private ComboBox comboBox;

    // Database adapter
    private ItemAdapter itemAdapter;
    private CatalogAdapter catalogAdapter;
    final ObservableList<String> data = FXCollections.observableArrayList();

    public void setModel(ItemAdapter itemAdapter, CatalogAdapter catalogAdapter){
        this.itemAdapter = itemAdapter;
        this.catalogAdapter = catalogAdapter;
        buildComboBoxData();
    }

    public void cancel() { // Close the window without saving
        Stage stage = (Stage)this.cancelButton.getScene().getWindow();
        stage.close();
    }

    public void save() {

        // Add the product to database
        try {
            if(comboBox.getValue() == null)
                BackgroundApp.displayAlert("Please Choose An Option");
            else if(name.getText().equals("") || size.getText().equals(""))
                BackgroundApp.displayAlert("Please Enter In A Name");
            else if(BackgroundApp.CheckInt(ID.getText()) && BackgroundApp.CheckDouble(price.getText())
                    && BackgroundApp.CheckInt(comboBox.getValue().toString().substring(0,1)) && BackgroundApp.CheckInt(numberInStock.getText())) {
                itemAdapter.insertItem(Integer.parseInt(ID.getText()), name.getText(), Double.parseDouble(price.getText()),
                        size.getText(), Integer.parseInt(numberInStock.getText()), Integer.parseInt(comboBox.getValue().toString().substring(0, 1)));
                BackgroundApp.displayAlert("Item Added!");

                comboBox.setValue(null);
                ID.clear();
                name.clear();
                price.clear();
                size.clear();
                numberInStock.clear();
            }
        }catch (SQLException e){
            BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on save");
        }
    }

    public void buildComboBoxData(){
        try {
            data.addAll(catalogAdapter.getCatalogsNameList());
        }catch (SQLException e){
            BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on build");
        }catch (NullPointerException ex){
            BackgroundApp.displayAlert("Catalog Non Existent");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(data);
    }
}
