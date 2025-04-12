package com.inventoryapp.services;

import com.inventoryapp.entities.Category;

import java.util.List;

public interface CategoryServices {

    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    Category update(Long id, Category category);
    String delete(Long id);
}
