package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.Category;
import com.inventoryapp.entities.Product;
import com.inventoryapp.repositories.CategoryRepository;
import com.inventoryapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImpl implements ProductServices{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return productRepository.findByCategory(category);
    }

    @Override
    public Product save(ProductDTO product) {
        Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);

        Product productEntity = new Product();
        productEntity.setCode(product.getCode());
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setAvailable(product.getAvailable());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setCategory(category);

        return productRepository.save(productEntity);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productUpdated = findById(id);
        productUpdated.setId(id);
        productUpdated.setName(product.getName());
//        productUpdated.setCategory(product.getCategory());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setQuantity(product.getQuantity());
        productUpdated.setAvailable(product.getAvailable());

        return productRepository.save(productUpdated);
    }

    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);

        return "product deleted";
    }
}
