package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.CategoryEntity;
import com.inventoryapp.entities.ProductEntity;
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
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public ProductEntity findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public List<ProductEntity> findByCategory(String categoryName) {
        CategoryEntity category = categoryRepository.findByName(categoryName);
        return productRepository.findByCategory(category);
    }

    @Override
    public ProductEntity save(ProductDTO product) {
        CategoryEntity category = categoryRepository.findById(product.getCategoryId()).orElse(null);

        ProductEntity productEntity = new ProductEntity();
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
    public ProductEntity update(Long id, ProductEntity product) {
        ProductEntity productUpdated = findById(id);
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
