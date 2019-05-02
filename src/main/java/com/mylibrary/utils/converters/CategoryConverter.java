package com.mylibrary.utils.converters;

import com.mylibrary.database.models.Category;
import com.mylibrary.modelFx.CategoryFx;

public class CategoryConverter {

    public static CategoryFx convertToCategoryFx(Category category) {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }

    public static Category convertCategoryFxToCategory(CategoryFx categoryFx) {
        Category category = new Category();
        category.setId(categoryFx.getId());
        category.setName(categoryFx.getName());
        return category;
    }
}
