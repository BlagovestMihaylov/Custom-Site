package com.example.site.web.controllers;

import com.example.site.core.models.Category;
import com.example.site.core.services.CategoryService;
import com.example.site.web.models.CategoryInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping(value = "/create")
    public Category createCategory(@RequestBody CategoryInput CategoryInput) {
       return categoryService.createCategory(CategoryInput.name);
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
