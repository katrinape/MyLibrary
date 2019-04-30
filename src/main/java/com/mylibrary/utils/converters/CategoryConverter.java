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
}
