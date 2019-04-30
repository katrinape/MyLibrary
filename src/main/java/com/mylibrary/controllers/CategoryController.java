package com.mylibrary.controllers;

import com.mylibrary.modelFx.CategoryFx;
import com.mylibrary.modelFx.CategoryModel;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;

public class CategoryController {

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button deleteCategoryBtn;

    @FXML
    private Button editCategoryBtn;

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;

    @FXML
    private TreeView<String> categoryTreeView;

    private CategoryModel categoryModel;

    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        try {
            this.categoryModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        this.categoryTreeView.setRoot(this.categoryModel.getRoot());
        initBindings();
    }

    private void initBindings() {
        this.addCategoryBtn.disableProperty().bind(this.categoryTextField.textProperty().isEmpty());
        this.deleteCategoryBtn.disableProperty().bind(categoryModel.categoryProperty().isNull());
        this.editCategoryBtn.disableProperty().bind(categoryModel.categoryProperty().isNull());
    }

    @FXML
    public void addCategory() {
        try {
            categoryModel.saveCategory(categoryTextField.getText());
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        categoryTextField.clear();
    }

    public void deleteCategory() {
        try {
            this.categoryModel.deleteCategoryById();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void onActionComboBox() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    public void editCategory() {
        String newCategoryName = DialogUtils.editDialog(categoryModel.getCategory().getName());
        if (!newCategoryName.trim().isBlank()) {
            this.categoryModel.getCategory().setName(newCategoryName);
            try {
                this.categoryModel.updateCategory();
            } catch (ApplicationException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }
    }
}
