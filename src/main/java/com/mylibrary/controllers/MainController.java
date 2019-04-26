package com.mylibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuController topMenuController;

    @FXML
    private void initialize() {
        topMenuController.setMainController(this);
    }
}
