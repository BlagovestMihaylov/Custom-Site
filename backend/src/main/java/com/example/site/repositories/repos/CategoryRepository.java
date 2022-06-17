package com.example.site.repositories.repos;

import com.example.site.repositories.models.CategoryDAO;

import java.util.List;

public interface CategoryRepository {
    CategoryDAO createCategory(String name);
    List<CategoryDAO> getAllCategories();

}
