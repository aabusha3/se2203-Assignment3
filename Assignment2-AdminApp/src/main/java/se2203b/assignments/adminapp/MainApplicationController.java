package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable {

    @FXML
    private MenuBar mainMenu;
    private Connection connection;
    private ItemAdapter itemAdapter;
    private CatalogAdapter catalogAdapter;

    public void showAbout() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddItem() throws Exception{
        // create Product model
        this.itemAdapter = new ItemAdapter(this.connection, false);
        this.catalogAdapter = new CatalogAdapter(this.connection, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItem-View.fxml"));
        Parent addProduct = fxmlLoader.load();
        AddItemController addItemController = fxmlLoader.getController();
        addItemController.setModel(this.itemAdapter, this.catalogAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the addProduct's UI elements to the stage
        stage.setScene(new Scene(addProduct));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditDeleteItem() throws Exception{
        // create Product model
        this.itemAdapter = new ItemAdapter(this.connection, false);
        this.catalogAdapter = new CatalogAdapter(this.connection, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeleteItem-View.fxml"));
        Parent EDProduct = fxmlLoader.load();
        EditDeleteItemController editDeleteItemController = fxmlLoader.getController();
        editDeleteItemController.setModel(this.itemAdapter, this.catalogAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the addProduct's UI elements to the stage
        stage.setScene(new Scene(EDProduct));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit and Delete Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddCatalog() throws Exception{
        // create Catalog model
        this.catalogAdapter = new CatalogAdapter(this.connection, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AddCatalog-view.fxml"));
        Parent addCatalog = (Parent) fxmlLoader.load();
        AddCatalogController addCatalogController = fxmlLoader.getController();
        addCatalogController.setModel(this.catalogAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the addProduct's UI elements to the stage
        stage.setScene(new Scene(addCatalog));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditDeleteCatalog() throws Exception{
        // create Product model
        this.catalogAdapter = new CatalogAdapter(this.connection, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("EditDeleteCatalog-view.fxml"));
        Parent eDCatalog = fxmlLoader.load();
        EditDeleteCatalogController editDeleteCatalogController = fxmlLoader.getController();
        editDeleteCatalogController.setModel(this.catalogAdapter, this.itemAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the addProduct's UI elements to the stage
        stage.setScene(new Scene(eDCatalog));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit or Delete Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void resetDB(){
        try {
            catalogAdapter = new CatalogAdapter(connection, true);
            displayAlert("Catalogs table has been created");
        } catch (SQLException e){
            displayAlert("ERROR: " + e.getMessage());
        }
        try {
            itemAdapter = new ItemAdapter(connection, true);
            displayAlert("Product table has been created");
        } catch (SQLException e){
            displayAlert("ERROR: " + e.getMessage());
        }
    }

    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFashionStoreDB;create=true";
            // Create a connection to the database
            connection = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

    // Display an alert when error occurs
    private void displayAlert(String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

}
