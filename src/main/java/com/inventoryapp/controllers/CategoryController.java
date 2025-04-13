package com.inventoryapp.controllers;

import com.inventoryapp.entities.Category;
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
    public List<Category> getAllProducts() {
        return categoryServices.findAll();
    }

    @GetMapping("/{id}")
    public Category getProductById(@PathVariable Long id) {
        return categoryServices.findById(id);
    }

    @PostMapping
    public Category createProduct(@RequestBody Category category) {
        return categoryServices.save(category);
    }

    @PutMapping("/{id}")
    public Category updateProduct(@PathVariable Long id, @RequestBody Category category) {
        return categoryServices.update(id, category);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return categoryServices.delete(id);
    }
}
