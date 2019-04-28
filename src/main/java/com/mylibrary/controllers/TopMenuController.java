package com.mylibrary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuController {

    private static final String LIBRARY_FXML = "/fxml/Library.fxml";
    private static final String LIST_BOOKS_FXML = "/fxml/ListBooks.fxml";
    private static final String STATISTICS_FXML = "/fxml/Statistics.fxml";
    private static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";

    private MainController mainController;

    @FXML
    public ToggleGroup toggleButtons;

    @FXML
    public void openLibrary() {
        mainController.setCenter(LIBRARY_FXML);
    }

    @FXML
    public void openBookList() {
        mainController.setCenter(LIST_BOOKS_FXML);
    }

    @FXML
    public void openStatistics() {
        mainController.setCenter(STATISTICS_FXML);
    }

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook() {
        if (toggleButtons.getSelectedToggle() != null) {
            toggleButtons.getSelectedToggle().setSelected(false);
        }
        mainController.setCenter(ADD_BOOK_FXML);
    }
}
