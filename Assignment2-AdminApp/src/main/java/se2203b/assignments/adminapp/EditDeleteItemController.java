package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditDeleteItemController implements Initializable {

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
    private TextField idEdit;
    @FXML
    private TextField idDelete;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label sizeLabel;
    @FXML
    private Label numberInStockLabel;
    @FXML
    private ComboBox comboBox;
    // ID input default value
    private int inputID;
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

    public void searchItemEdit() throws SQLException{
        if(BackgroundApp.CheckInt(idEdit.getText())) {
            inputID = Integer.parseInt(idEdit.getText());
            Item searchedItem = itemAdapter.searchItem(inputID);
            System.out.println(searchedItem.getCatalogID());
            try {
                name.setText(searchedItem.getName());
                price.setText(String.valueOf(searchedItem.getPrice()));
                size.setText(searchedItem.getSize());
                numberInStock.setText(String.valueOf(searchedItem.getNumberInStock()));
            } catch (NullPointerException e) {
                comboBox.setValue("Non Existent");
                name.setText("Non Existent");
                price.setText("Non Existent");
                size.setText("Non Existent");
                numberInStock.setText("Non Existent");
            }
        }
    }

    public void searchItemDelete() throws SQLException{
        if(BackgroundApp.CheckInt(idDelete.getText())) {
            inputID = Integer.parseInt(idDelete.getText());
            Item searchedItem = itemAdapter.searchItem(inputID);
            try {
                nameLabel.setText(searchedItem.getName());
                priceLabel.setText(String.valueOf(searchedItem.getPrice()));
                sizeLabel.setText(searchedItem.getSize());
                numberInStockLabel.setText(String.valueOf(searchedItem.getNumberInStock()));
            } catch (NullPointerException e) {
                nameLabel.setText("Non Existent");
                priceLabel.setText("Non Existent");
                sizeLabel.setText("Non Existent");
                numberInStockLabel.setText("Non Existent");
            }
        }
    }

    public void onEditClick() throws SQLException {

        // Edit the product to database
        try {
            if(comboBox.getValue() == null)
                BackgroundApp.displayAlert("Please Choose An Option");
            else if(name.getText().equals("") || size.getText().equals(""))
                BackgroundApp.displayAlert("Please Enter In A Name");
            else if(BackgroundApp.CheckInt(idEdit.getText()) && BackgroundApp.CheckDouble(price.getText())
                    && BackgroundApp.CheckInt(comboBox.getValue().toString().substring(0,1)) && BackgroundApp.CheckInt(numberInStock.getText())) {
                itemAdapter.updateItem(Integer.parseInt(idEdit.getText()), name.getText(), Double.parseDouble(price.getText()),
                        size.getText(), Integer.parseInt(numberInStock.getText()), Integer.parseInt(comboBox.getValue().toString().substring(0, 1)));
                BackgroundApp.displayAlert("Item Edited!");

                comboBox.setValue(null);
                idEdit.clear();
                name.clear();
                price.clear();
                size.clear();
                numberInStock.clear();
            }
        }catch (SQLException e){
            BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on edit");
        }
    }

    public void onDeleteClick() throws SQLException{

        // Delete the product using ID
        try {
            itemAdapter.deleteItem(Integer.parseInt(idDelete.getText()));
            BackgroundApp.displayAlert("Item Deleted!");

            nameLabel.setText("Result Will Displayed Here");
            priceLabel.setText("Result Will Displayed Here");
            sizeLabel.setText("Result Will Displayed Here");
            numberInStockLabel.setText("Result Will Displayed Here");

        }catch (SQLException e){
            BackgroundApp.displayAlert("SQLException: " + e.getMessage() + " on delete");
        }
        catch (NullPointerException ex){
            BackgroundApp.displayAlert("Item Not Found");
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
