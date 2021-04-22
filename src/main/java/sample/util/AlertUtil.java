package sample.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void openAlert(String title, String msg, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.titleProperty().set(title);
        alert.headerTextProperty().set(msg);
        alert.showAndWait();
    }
}
