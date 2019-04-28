package com.mylibrary.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    static ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    public static void dialogAboutApp() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about"));
        informationAlert.setHeaderText(bundle.getString("about_header"));
        informationAlert.setContentText(bundle.getString("about_content"));
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(bundle.getString("exit_title"));
        confirmationAlert.setHeaderText(bundle.getString("exit_header"));
        return confirmationAlert.showAndWait();

    }
}
