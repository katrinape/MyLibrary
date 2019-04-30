package com.mylibrary.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

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

    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error_title"));
        errorAlert.setHeaderText(bundle.getString("error_header"));
        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle(bundle.getString("edit_title"));
        dialog.setHeaderText(bundle.getString("edit_header"));
        dialog.setContentText("edit_content");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
