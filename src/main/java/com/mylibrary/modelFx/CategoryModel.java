package com.mylibrary.modelFx;

import com.mylibrary.database.dao.CategoryDao;
import com.mylibrary.database.models.Category;
import com.mylibrary.utils.DialogUtils;
import com.mylibrary.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();

    public void init() {
        CategoryDao categoryDao = new CategoryDao();
        this.categoryList.clear();
        try {
            List<Category> categories = categoryDao.queryForAll(Category.class);
            categories.forEach(c -> {
                CategoryFx categoryFx = new CategoryFx();
                categoryFx.setId(c.getId());
                categoryFx.setName(c.getName());
                this.categoryList.add(categoryFx);
            });
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteCategoryById() {
        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.deleteById(Category.class, category.getValue().getId());
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void saveCategory(String name) {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        try {
            categoryDao.createOrUpdate(category);
            init();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
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
