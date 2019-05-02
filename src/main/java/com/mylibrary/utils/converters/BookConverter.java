package com.mylibrary.utils.converters;

import com.mylibrary.database.models.Book;
import com.mylibrary.modelFx.BookFx;
import com.mylibrary.utils.Utils;

public class BookConverter {

    public static Book convertToBook(BookFx bookFx) {
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

    public static BookFx convertToBookFx(Book book) {
        BookFx bookFx = new BookFx();
        bookFx.setId(book.getId());
        bookFx.setTitle(book.getTitle());
        bookFx.setIsbn(book.getIsbn());
        bookFx.setRating(book.getRating());
        bookFx.setDescription(book.getDescription());
        bookFx.setReleaseDate(Utils.convertToLocalDate(book.getReleaseDate()));
        bookFx.setAuthorFx(AuthorConverter.convertToAuthorFx(book.getAuthor()));
        bookFx.setCategoryFx(CategoryConverter.convertToCategoryFx(book.getCategory()));
        return bookFx;
    }
}
