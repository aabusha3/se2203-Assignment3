package se2203b.assignments.adminapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BackgroundApp {
    public static boolean CheckInt(String numToCheck) {
        boolean isInt = true;
        try {
            int num = Integer.parseInt(numToCheck);
            if(num < 0)
                throw new Exception();
        } catch (NumberFormatException e) {
            displayAlert("Please Only Enter Integers");
            isInt = false;
        } catch (Exception e) {
            displayAlert("Please Only Enter Positive Integers");
            isInt = false;
        }
        return isInt;
    }

    public static boolean CheckDouble(String numToCheck) {
        boolean isDouble = true;
        try {
            double num = Double.parseDouble(numToCheck);
            if(num < 0)
                throw new Exception();
        } catch (NumberFormatException e) {
            displayAlert("Please Only Enter Doubles");
            isDouble = false;
        } catch (Exception e) {
            displayAlert("Please Only Enter Positive Doubles");
            isDouble = false;
        }
        return isDouble;
    }

    //displays a window alert
    public static void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(BackgroundApp.class.getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }
}
