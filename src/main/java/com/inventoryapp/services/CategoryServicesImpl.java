package com.inventoryapp.services;

import com.inventoryapp.dtos.CategoryDTO;
import com.inventoryapp.entities.Category;
import com.inventoryapp.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(CategoryDTO category) {
        Category category1 = modelMapper.map(category, Category.class);
        categoryRepository.save(category1);

        return modelMapper.map(category1, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO category) {
        Category oldCategory = categoryRepository.findById(id).orElse(null);
        oldCategory.setId(id);
        oldCategory.setName(category.getName());
        oldCategory.setDescription(category.getDescription());
        oldCategory.setIsActive(category.getIsActive());

        categoryRepository.save(oldCategory);

        return modelMapper.map(oldCategory, CategoryDTO.class);
    }

    @Override
    public String delete(Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
