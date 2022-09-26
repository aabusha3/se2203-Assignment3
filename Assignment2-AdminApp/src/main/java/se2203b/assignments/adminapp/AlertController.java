package se2203b.assignments.adminapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AlertController implements Initializable {
    @FXML
    public Label error;

    public AlertController() {
    }

    public void setAlertText(String text) {
        this.error.setText(text);
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}

