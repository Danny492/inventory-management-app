package com.inventoryapp.controllers;

import com.inventoryapp.dtos.CategoryDTO;
import com.inventoryapp.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public List<CategoryDTO> getAllProducts() {
        return categoryServices.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getProductById(@PathVariable Long id) {
        return categoryServices.findById(id);
    }

    @PostMapping
    public CategoryDTO createProduct(@RequestBody CategoryDTO category) {
        return categoryServices.save(category);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateProduct(@PathVariable Long id, @RequestBody CategoryDTO category) {
        return categoryServices.update(id, category);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return categoryServices.delete(id);
    }
}
