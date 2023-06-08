package com.pembo.store.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.pembo.store.dto.CategoryDto;
import com.pembo.store.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CayegoryController {

    private final CategoryService categoryService;

    public CayegoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
    
}
