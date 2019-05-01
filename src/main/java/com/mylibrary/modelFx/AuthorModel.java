package com.mylibrary.modelFx;

import com.mylibrary.database.dao.AuthorDao;
import com.mylibrary.database.models.Author;
import com.mylibrary.utils.converters.AuthorConverter;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        this.authorFxObservableList.clear();
        List<Author> authors = authorDao.queryForAll(Author.class);
        authors.forEach(a -> authorFxObservableList.add(AuthorConverter.convertAuthorToAuthorFx(a)));
    }

    public void saveAuthor() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.createOrUpdate(AuthorConverter.convertAuthorFxToAuthor(this.getAuthorFxObject()));
        init();
    }

    public AuthorFx getAuthorFxObject() {
        return authorFxObjectProperty.get();
    }

    public ObjectProperty<AuthorFx> authorFxObjectProperty() {
        return authorFxObjectProperty;
    }

    public void setAuthorFxObjectProperty(AuthorFx authorFxObjectProperty) {
        this.authorFxObjectProperty.set(authorFxObjectProperty);
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
