package com.mylibrary.controllers;

import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.FxmlUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public class MainController {

    @FXML
    public ToggleGroup styleGroup;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuController topMenuController;

    @FXML
    private void initialize() {
        topMenuController.setMainController(this);
    }

    public void setCenter(String fxmlPath) {
        borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }

    public void closeApp() {
        Optional<ButtonType> result = DialogUtils.confirmationDialog();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setModena() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);
    }

    public void showAbout(ActionEvent actionEvent) {
        DialogUtils.dialogAboutApp();
    }
}
