package com.example.ideas.util_Entities.category.controller;

import com.example.ideas.util_Entities.category.model.Category;
import com.example.ideas.util_Entities.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/id/{category_id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("category_id") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId).orElse(null);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{category_name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("category_name") String categoryName){
        Category category = categoryService.getCategoryByName(categoryName).orElse(null);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

}
