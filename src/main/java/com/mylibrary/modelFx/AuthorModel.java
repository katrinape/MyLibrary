package com.mylibrary.modelFx;

import com.mylibrary.database.dao.AuthorDao;
import com.mylibrary.utils.converters.AuthorConverter;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AuthorModel {

    private ObjectProperty<AuthorFx> authorFxObjectProperty = new SimpleObjectProperty<>(new AuthorFx());

    public void saveAuthor() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        authorDao.createOrUpdate(AuthorConverter.convertAuthorFxToAuthor(this.getAuthorFxObject()));
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
}
