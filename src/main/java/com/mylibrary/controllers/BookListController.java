package com.mylibrary.controllers;

import com.mylibrary.modelFx.*;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.FxmlUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TableColumn<BookFx, String> descColumn;
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
    @FXML
    public TableColumn<BookFx, BookFx> deleteColumn;
    @FXML
    private TableColumn<BookFx, BookFx> editColumn;

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
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.deleteColumn.setCellFactory(param -> new TableCell<>() {
            Button button = createButton("/icons/delete.png");
            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setOnAction(actionEvent -> {
                        try {
                            bookListModel.deleteBook(item);
                        } catch (ApplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });

        this.editColumn.setCellFactory(param -> new TableCell<>() {
            Button button = createButton("/icons/edit.png");
            @Override
            protected void updateItem(BookFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                    button.setOnAction(actionEvent -> {
                        FXMLLoader loader = FxmlUtils.getLoader("/fxml/AddBook.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        BookController controller = loader.getController();
                        controller.getBookModel().setBookFxObjectProperty(item);
                        controller.initBindings();

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    });
                }
            }
        });
    }

    private Button createButton(String path) {
        Button button = new Button();
        button.setGraphic(new ImageView(new Image(this.getClass().getResource(path).toString())));
        return button;
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
