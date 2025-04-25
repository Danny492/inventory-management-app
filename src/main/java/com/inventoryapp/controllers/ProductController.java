package com.inventoryapp.controllers;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.services.ProductServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServicesImpl productServices;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        if(productServices.findAll().isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productServices.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productServices.findById(id);
        if(productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO product) {
        ProductDTO productDTO = productServices.save(product);
        if(productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO product) {
        ProductDTO productDTO = productServices.update(id, product);
        if(productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        String message = productServices.delete(id);
        if(message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
