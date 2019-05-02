package com.mylibrary.modelFx;

import com.mylibrary.database.dao.AuthorDao;
import com.mylibrary.database.dao.BookDao;
import com.mylibrary.database.dao.CategoryDao;
import com.mylibrary.database.models.Author;
import com.mylibrary.database.models.Book;
import com.mylibrary.database.models.Category;
import com.mylibrary.utils.converters.AuthorConverter;
import com.mylibrary.utils.converters.BookConverter;
import com.mylibrary.utils.converters.CategoryConverter;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookListModel {

    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private List<BookFx> bookFxList = new ArrayList<>();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> this.bookFxList.add(BookConverter.convertToBookFx(book)));
        this.bookFxObservableList.addAll(this.bookFxList);
        initAuthors();
        initCategories();
    }

    public void filterBookList() {
        if (getAuthorFxObjectProperty() != null && getCategoryFxObjectProperty() != null) {
            filterPredicate(predicateAuthor().and(predicateCategory()));
        } else if (getCategoryFxObjectProperty() != null) {
            filterPredicate(predicateCategory());
        } else if (getAuthorFxObjectProperty() != null) {
            filterPredicate(predicateAuthor());
        } else {
            this.bookFxObservableList.setAll(this.bookFxList);
        }
    }

    private void initAuthors() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authors = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authors.forEach(author -> authorFxObservableList.add(AuthorConverter.convertToAuthorFx(author)));
    }

    private void initCategories() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categories.forEach(category -> categoryFxObservableList.add(CategoryConverter.convertToCategoryFx(category)));
    }

    private Predicate<BookFx> predicateCategory() {
        return bookFx -> bookFx.getCategoryFx().getId() == getCategoryFxObjectProperty().getId();
    }

    private Predicate<BookFx> predicateAuthor() {
        return bookFx -> bookFx.getAuthorFx().getId() == getAuthorFxObjectProperty().getId();
    }

    private void filterPredicate(Predicate<BookFx> predicate) {
        List<BookFx> newList = bookFxList.stream().filter(predicate).collect(Collectors.toList());
        this.bookFxObservableList.setAll(newList);
    }

    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public AuthorFx getAuthorFxObjectProperty() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectPropertyProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }
}
