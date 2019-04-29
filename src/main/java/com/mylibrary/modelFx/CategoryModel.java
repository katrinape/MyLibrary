package com.mylibrary.modelFx;

import com.mylibrary.database.dao.CategoryDao;
import com.mylibrary.database.models.Category;
import com.mylibrary.utils.exceptions.ApplicationException;

public class CategoryModel {

    public void saveCategory(String name) {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setName(name);
        try {
            categoryDao.createOrUpdate(category);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
