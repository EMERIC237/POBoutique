package com.pembo.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pembo.store.dto.CategoryDto;
import com.pembo.store.exception.ResourceNotFoundException;
import com.pembo.store.mapper.CategoryMapper;
import com.pembo.store.model.Category;
import com.pembo.store.model.Product;
import com.pembo.store.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(findCategoryById(id));
    }

    public Optional<CategoryDto> getCategoryByName(String name) {
        return categoryRepository.findByName(name.toUpperCase()).map(categoryMapper::toDto);
    }

    /**
     * save category
     *
     * @param category
     * @return saved category
     */
    public CategoryDto saveCategory(CategoryDto category) {
        // Convert name to uppercase
        CategoryDto categoryToSave = new CategoryDto(category.id(), category.name().toUpperCase());

        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryToSave)));
    }

    public CategoryDto createOrFindCategory(String category) {
        return getCategoryByName(category).orElseGet(() -> saveCategory(new CategoryDto(null, category)));
    }


    /**
     * update category
     *
     * @param id
     * @param category
     * @return updated category
     */
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto category) {
        Category toUpdateCategory = findCategoryById(id);
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

    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
    }

}
