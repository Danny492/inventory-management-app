package com.inventoryapp.services;

import com.inventoryapp.entities.Category;
import com.inventoryapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category oldCategory = categoryRepository.findById(id).orElse(null);
        oldCategory.setId(id);
        oldCategory.setName(category.getName());
        oldCategory.setDescription(category.getDescription());
        oldCategory.setIsActive(category.getIsActive());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public String delete(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
