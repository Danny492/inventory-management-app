package com.inventoryapp.controllers;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.services.ProductServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServicesImpl productServices;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productServices.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productServices.findById(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        return productServices.save(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {



        return productServices.update(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productServices.delete(id);
    }

//    @GetMapping("/byName")
//    public ProductEntity getProductByName(@RequestParam String name) {
//        return productServices.findByName(name);
//    }
//
//    @GetMapping("/byCode")
//    public ProductEntity getProductByCode(@RequestParam String code) {
//        return productServices.findByCode(code);
//    }
//
//    @GetMapping("/byCategory")
//    public List<ProductEntity> getProductByCategory(@RequestParam String categoryName) {
//        return productServices.findByCategory(categoryName);
//    }

}
