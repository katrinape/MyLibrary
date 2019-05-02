package com.mylibrary.modelFx;

import com.mylibrary.database.dao.BookDao;
import com.mylibrary.database.models.Book;
import com.mylibrary.utils.converters.BookConverter;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class BookListModel {

    private ObservableList<BookFx> bookFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> this.bookFxObservableList.add(BookConverter.convertToBookFx(book)));
    }

    public ObservableList<BookFx> getBookFxObservableList() {
        return bookFxObservableList;
    }

    public void setBookFxObservableList(ObservableList<BookFx> bookFxObservableList) {
        this.bookFxObservableList = bookFxObservableList;
    }
}
