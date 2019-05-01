package com.mylibrary.controllers;

import com.mylibrary.modelFx.AuthorModel;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AuthorController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button addButton;

    private AuthorModel authorModel;

    public void initialize() {
        this.authorModel = new AuthorModel();
        this.authorModel.authorFxObjectProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.authorModel.authorFxObjectProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty()
                .or(this.surnameTextField.textProperty().isEmpty()));
    }

    @FXML
    public void addAuthor() {
        try {
            this.authorModel.saveAuthor();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }
}
