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

    @FXML
    private Button deleteCategoryBtn;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        initBindings();
    }

    private void initBindings() {
        this.addCategoryBtn.disableProperty().bind(this.categoryTextField.textProperty().isEmpty());
        this.deleteCategoryBtn.disableProperty().bind(categoryModel.categoryProperty().isNull());
    }

    @FXML
    public void addCategory() {
        categoryModel.saveCategory(categoryTextField.getText());
        categoryTextField.clear();
    }

    public void deleteCategory() {
        this.categoryModel.deleteCategoryById();
        this.categoryModel.init();
    }

    public void onActionComboBox() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }
}
