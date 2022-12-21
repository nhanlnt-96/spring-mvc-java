package com.main.comic.service.category;

import com.main.comic.dao.category.CategoryDAO;
import com.main.comic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImplement implements CategoryService {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImplement(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    @Transactional
    public void saveCategoryData(Category categoryData) {
        categoryDAO.saveCategoryData(categoryData);
    }

    @Override
    @Transactional
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Override
    @Transactional
    public void removeCategoryById(int categoryId) {
        categoryDAO.removeCategoryById(categoryId);
    }

    @Override
    @Transactional
    public boolean checkCategoryIsExist(String categoryName, int categoryId) {
        return categoryDAO.checkCategoryIsExist(categoryName, categoryId);
    }
}
