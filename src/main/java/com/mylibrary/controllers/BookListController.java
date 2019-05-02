package com.mylibrary.controllers;

import com.mylibrary.modelFx.AuthorFx;
import com.mylibrary.modelFx.BookFx;
import com.mylibrary.modelFx.BookListModel;
import com.mylibrary.modelFx.CategoryFx;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class BookListController {
    
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private ComboBox<AuthorFx> authorComboBox;
    @FXML
    private TableView<BookFx> bookTableView;
    @FXML
    private TableColumn<BookFx, String> titleColumn;
    @FXML
    private TableColumn<BookFx, String > descColumn;
    @FXML
    private TableColumn<BookFx, AuthorFx> authorColumn;
    @FXML
    private TableColumn<BookFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<BookFx, Number> ratingColumn;
    @FXML
    private TableColumn<BookFx, String> isbnColumn;
    @FXML
    private TableColumn<BookFx, LocalDate> releaseColumn;

    private BookListModel bookListModel;

    public void initialize() {
        this.bookListModel = new BookListModel();
        try {
            this.bookListModel.init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        initComboBoxes();
        initTableView();
    }

    private void initComboBoxes() {
        this.categoryComboBox.setItems(this.bookListModel.getCategoryFxObservableList());
        this.authorComboBox.setItems(this.bookListModel.getAuthorFxObservableList());
        this.bookListModel.categoryFxObjectPropertyProperty().bind(this.categoryComboBox.valueProperty());
        this.bookListModel.authorFxObjectPropertyProperty().bind(this.authorComboBox.valueProperty());
    }

    private void initTableView() {
        this.bookTableView.setItems(this.bookListModel.getBookFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxProperty());
        this.ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        this.isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        this.releaseColumn.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
    }

    public void filter() {
        this.bookListModel.filterBookList();
    }

    public void clearAuthor() {
        this.authorComboBox.getSelectionModel().clearSelection();
    }

    public void clearCategory() {
        this.categoryComboBox.getSelectionModel().clearSelection();
    }
}
