package com.mylibrary.utils.converters;

import com.mylibrary.database.models.Author;
import com.mylibrary.modelFx.AuthorFx;

public class AuthorConverter {

    public static Author convertAuthorFxToAuthor(AuthorFx authorFx) {
        Author author = new Author();
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }
}
