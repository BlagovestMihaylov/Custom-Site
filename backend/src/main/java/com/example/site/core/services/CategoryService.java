package com.example.site.core.services;

import com.example.site.core.models.Category;
import com.example.site.repositories.repos.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(String name) {
        return Mapper.fromCategoryDAO(categoryRepository.createCategory(name));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories()
                .stream()
                .map(Mapper::fromCategoryDAO)
                .collect(Collectors.toList());
    }
}
