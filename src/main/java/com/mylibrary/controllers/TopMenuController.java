package com.mylibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuController {

    private MainController mainController;

    @FXML
    public ToggleGroup toggleButtons;

    @FXML
    public void openLibrary() {
        System.out.println("Open Library");
    }

    @FXML
    public void openBookList() {
        System.out.println("Open Book List");
    }

    @FXML
    public void openStatistics() {
        System.out.println("Open Statistics");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
