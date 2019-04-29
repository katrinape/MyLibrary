package com.mylibrary.controllers;

import com.mylibrary.modelFx.CategoryFx;
import com.mylibrary.modelFx.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CategoryController {

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button addCategoryBtn;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        initBindings();
    }

    private void initBindings() {
        addCategoryBtn.disableProperty().bind(categoryTextField.textProperty().isEmpty());
    }

    @FXML
    public void addCategory() {
        categoryModel.saveCategory(categoryTextField.getText());
        categoryTextField.clear();
    }
}
