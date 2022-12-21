package com.main.comic.dao.category;

import com.main.comic.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();

    void saveCategoryData(Category categoryData);

    Category getCategoryById(int categoryId);

    void removeCategoryById(int categoryId);

    boolean checkCategoryIsExist(String categoryName, int categoryId);
}
