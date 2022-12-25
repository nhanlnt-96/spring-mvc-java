package com.main.comic.controller;

import com.main.comic.entity.Category;
import com.main.comic.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category-list")
    public String getAllCategory(Model theModel) {
        List<Category> theCategories = categoryService.getAllCategories();

        theModel.addAttribute("categories", theCategories);

        return "admin/category-list";
    }

    @GetMapping("/add-category")
    public String showFormForAdd(Model theModel) {
        Category theCategory = new Category();

        theModel.addAttribute("category", theCategory);

        return "admin/category-form";
    }

    @PostMapping("/save-category")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "admin/category-form";
        } else {
            if (categoryService.checkCategoryIsExist(category.getCategoryName(), category.getId())) {
                theModel.addAttribute("duplicateCategoryError", "Category already exist");

                theModel.addAttribute("categoryData", category);

                return "admin/category-form";
            } else {
                categoryService.saveCategoryData(category);

                return "redirect:/admin/category/category-list";
            }
        }
    }

    @GetMapping("/update-category")
    public String showFormForUpdate(@RequestParam("categoryId") int theId, Model theModel) {
        Category theCategory = categoryService.getCategoryById(theId);

        theModel.addAttribute("category", theCategory);

        return "admin/category-form";
    }

    @GetMapping("/remove-category")
    public String deleteCategory(@RequestParam("categoryId") int theId) {
        categoryService.removeCategoryById(theId);

        return "redirect:/admin/category/category-list";
    }
}
