package com.pembo.store.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.mapper.CategoryMapper;
import com.pembo.store.model.Category;
import com.pembo.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * get all categories
     * 
     * @return List of all categories
     */
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    /**
     * get category by id
     * 
     * @param id
     * @return category
     */
    public CategoryDto getCategoryById(Long id){
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not found")));
    }

    /**
     * save category
     * 
     * @param category
     * @return saved category
     */
    public CategoryDto saveCategory(CategoryDto category) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(category)));
    }

    /**
     * update category
     * 
     * @param id
     * @param category
     * @return updated category
     */
    public CategoryDto updateCategory(Long id, CategoryDto category) {
        Category toUpdateCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.partialUpdate(category, toUpdateCategory);
        Category savedCategory = categoryRepository.save(toUpdateCategory);
        return categoryMapper.toDto(savedCategory);
    }


    /**
     * delete category by id
     * 
     * @param id
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
