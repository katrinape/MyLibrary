package com.mylibrary.utils.converters;

import com.mylibrary.database.models.Book;
import com.mylibrary.modelFx.BookFx;
import com.mylibrary.utils.Utils;

public class BookConverter {

    public static Book convertBookFxToBook(BookFx bookFx) {
        Book book = new Book();
        book.setId(bookFx.getId());
        book.setTitle(bookFx.getTitle());
        book.setIsbn(bookFx.getIsbn());
        book.setRating(bookFx.getRating());
        book.setDescription(bookFx.getDescription());
        book.setReleaseDate(Utils.convertToDate(bookFx.getReleaseDate()));
        book.setAddedDate(Utils.convertToDate(bookFx.getAddedDate()));
        return book;
    }
}
