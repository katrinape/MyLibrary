package com.mylibrary.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BookFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private ObjectProperty<AuthorFx> authorFx = new SimpleObjectProperty<>();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> releaseDate = new SimpleObjectProperty<>();
    private SimpleStringProperty isbn = new SimpleStringProperty();
    private SimpleIntegerProperty rating = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>(LocalDate.now());

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CategoryFx getCategoryFx() {
        return categoryFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxProperty() {
        return categoryFx;
    }

    public void setCategoryFx(CategoryFx categoryFx) {
        this.categoryFx.set(categoryFx);
    }

    public AuthorFx getAuthorFx() {
        return authorFx.get();
    }

    public ObjectProperty<AuthorFx> authorFxProperty() {
        return authorFx;
    }

    public void setAuthorFx(AuthorFx authorFx) {
        this.authorFx.set(authorFx);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDate getReleaseDate() {
        return releaseDate.get();
    }

    public ObjectProperty<LocalDate> releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public LocalDate getAddedDate() {
        return addedDate.get();
    }

    public ObjectProperty<LocalDate> addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate.set(addedDate);
    }

    @Override
    public String toString() {
        return "BookFx{" +
                "id=" + id.get() +
                ", categoryFx=" + categoryFx.get() +
                ", authorFx=" + authorFx.get() +
                ", title=" + title.get() +
                ", description=" + description.get() +
                ", releaseDate=" + releaseDate.get() +
                ", isbn=" + isbn.get() +
                ", rating=" + rating.get() +
                ", addedDate=" + addedDate.get() +
                '}';
    }
}
