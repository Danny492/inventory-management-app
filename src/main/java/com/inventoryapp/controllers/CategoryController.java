package com.inventoryapp.controllers;

import com.inventoryapp.dtos.CategoryDTO;
import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        if(categoryServices.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(categoryServices.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryServices.findById(id);
        if(categoryDTO != null) {
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO category) {
        CategoryDTO categoryDTO = categoryServices.save(category);
        if(categoryDTO != null) {
            return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category) {
        CategoryDTO categoryUpdated = categoryServices.update(id, category);
        if(categoryUpdated != null) {
            return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        String message = categoryServices.delete(id);
        if(message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
