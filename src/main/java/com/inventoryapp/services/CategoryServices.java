package com.inventoryapp.services;

import com.inventoryapp.dtos.CategoryDTO;
import com.inventoryapp.entities.Category;

import java.util.List;

public interface CategoryServices {

    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO save(CategoryDTO category);
    CategoryDTO update(Long id, CategoryDTO category);
    String delete(Long id);
}
