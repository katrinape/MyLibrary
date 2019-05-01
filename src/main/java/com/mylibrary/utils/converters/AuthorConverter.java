package com.mylibrary.utils.converters;

import com.mylibrary.database.models.Author;
import com.mylibrary.modelFx.AuthorFx;

public class AuthorConverter {

    public static Author convertAuthorFxToAuthor(AuthorFx authorFx) {
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }

    public static AuthorFx convertAuthorToAuthorFx(Author author) {
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        authorFx.setSurname(author.getSurname());
        return authorFx;
    }
}
