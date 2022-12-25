package com.main.comic.controller;

import com.main.comic.entity.Category;
import com.main.comic.entity.Comic;
import com.main.comic.service.category.CategoryService;
import com.main.comic.service.comic.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/comic")
public class ComicController {
    private final ComicService comicService;
    private final CategoryService categoryService;

    @Autowired
    public ComicController(ComicService comicService, CategoryService categoryService) {
        this.comicService = comicService;
        this.categoryService = categoryService;
    }

    @GetMapping("/comic-list")
    public String getAllComic(Model theModel) {
        List<Comic> theComics = comicService.getAllComics();

        theModel.addAttribute("comics", theComics);

        return "admin/comic-list";
    }

    @GetMapping("/add-comic")
    public String showFormForAdd(Model theModel) {
        Comic theComic = new Comic();
        List<Category> categories = categoryService.getAllCategories();

        theModel.addAttribute("comic", theComic);
        theModel.addAttribute("categories", categories);

        return "admin/comic-form";
    }

    @PostMapping("/save-comic")
    public String saveComic(@Valid @ModelAttribute("comic") Comic comic, BindingResult bindingResult, Model theModel) {
        List<Category> categories = categoryService.getAllCategories();

        theModel.addAttribute("categories", categories);

        if (bindingResult.hasErrors()) {
            return "admin/comic-form";
        } else {
            if (comicService.checkComicIsExist(comic.getComicName(), comic.getId(), Integer.parseInt(comic.getCategorySelected()))) {
                theModel.addAttribute("duplicateComicError", "Comic already exist");

                theModel.addAttribute("comic", comic);

                return "admin/comic-form";
            } else {
                if (comic.getId() == 0) {
                    comic.setCreatedAt(new Date());
                } else {
                    Comic theComic = comicService.getComicById(comic.getId());
                    comic.setCreatedAt(theComic.getCreatedAt());
                }

                if (comic.getCategorySelected() != null) {
                    comic.setCategory(categoryService.getCategoryById(Integer.parseInt(comic.getCategorySelected())));
                }

                comic.setUpdatedAt(new Date());

                comicService.saveComicData(comic);
                return "redirect:/admin/comic/comic-list";
            }
        }
    }

    @GetMapping("/update-comic")
    public String showFormForUpdate(@RequestParam("comicId") int theId, Model theModel) {
        Comic theComic = comicService.getComicById(theId);
        List<Category> categories = categoryService.getAllCategories();

        theComic.setCategorySelected(Integer.toString(theComic.getCategory().getId()));

        theModel.addAttribute("categories", categories);
        theModel.addAttribute("comic", theComic);


        return "admin/comic-form";
    }

    @GetMapping("/remove-comic")
    public String deleteComic(@RequestParam("comicId") int theId) {
        comicService.removeComicById(theId);

        return "redirect:/admin/comic/comic-list";
    }
}
