package com.mylibrary.controllers;

import com.mylibrary.modelFx.AuthorFx;
import com.mylibrary.modelFx.BookModel;
import com.mylibrary.modelFx.CategoryFx;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookController {

    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private Slider ratingSlider;
    @FXML
    private TextField isbnTextField;
    @FXML
    private DatePicker releaseDatePicker;
    @FXML
    private Button addButton;

    private BookModel bookModel;

    public void initialize() {
        this.bookModel = new BookModel();
        try {
            this.bookModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        initBindings();
    }

    void initBindings() {
        this.categoryComboBox.setItems(this.bookModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.bookModel.getAuthorFxObservableList());

        this.authorComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().authorFxProperty());
        this.categoryComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().categoryFxProperty());
        this.titleTextField.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().titleProperty());
        this.descTextArea.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().descriptionProperty());
        this.ratingSlider.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().ratingProperty());
        this.isbnTextField.textProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().isbnProperty());
        this.releaseDatePicker.valueProperty().bindBidirectional(this.bookModel.getBookFxObjectProperty().releaseDateProperty());

        this.addButton.disableProperty().bind(this.titleTextField.textProperty().isEmpty().or(
                this.bookModel.getBookFxObjectProperty().categoryFxProperty().isNull().or(
                        this.bookModel.getBookFxObjectProperty().authorFxProperty().isNull())));
    }

    @FXML
    private void addBook() {
        System.out.println(this.bookModel.getBookFxObjectProperty().toString());
        try {
            this.bookModel.saveBook();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        clearSelection();
    }

    private void clearSelection() {
        this.categoryComboBox.getSelectionModel().clearSelection();
        this.authorComboBox.getSelectionModel().clearSelection();
        this.titleTextField.clear();
        this.descTextArea.clear();
        this.ratingSlider.setValue(this.ratingSlider.getMin());
        this.isbnTextField.clear();
        this.releaseDatePicker.getEditor().clear();
    }

    BookModel getBookModel() {
        return bookModel;
    }
}
