package com.mylibrary.modelFx;

import com.mylibrary.database.dao.CategoryDao;
import com.mylibrary.database.models.Category;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();

    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        this.categoryList.clear();

        List<Category> categories = categoryDao.queryForAll(Category.class);
        categories.forEach(c -> {
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());
            categoryFx.setName(c.getName());
            this.categoryList.add(categoryFx);
        });
    }

    public void deleteCategoryById() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.deleteById(Category.class, category.getValue().getId());
        init();
    }

    public void saveCategory(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        categoryDao.createOrUpdate(category);
        init();
    }

    public void updateCategory() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findById(Category.class, this.category.getValue().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.createOrUpdate(tempCategory);
        init();
    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
}
