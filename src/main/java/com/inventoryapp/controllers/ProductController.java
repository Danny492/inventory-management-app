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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        logger.info("Get product by id: " + id);
        ProductDTO productDTO = productServices.findById(id);
        logger.info("Get product by id: " + productDTO);
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

    @GetMapping("/byName/")
    public ResponseEntity<?> getProductByName(@RequestParam String name) {
        ProductDTO productDTO = productServices.findByName(name);
        if(productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCode/")
    public ResponseEntity<?> getProductByCode(@RequestParam String code) {
        ProductDTO productDTO = productServices.findByCode(code);
        if( productDTO != null) {
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byCategory/")
    public ResponseEntity<?> getProductByCategory(@RequestParam String categoryName) {
        List<ProductDTO> productDTOList = productServices.findByCategory(categoryName);
        if (productDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        }
    }

}
