package com.inventoryapp.services;

import com.inventoryapp.controllers.ProductController;
import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.Category;
import com.inventoryapp.entities.Product;
import com.inventoryapp.entities.Warehouse;
import com.inventoryapp.repositories.CategoryRepository;
import com.inventoryapp.repositories.ProductRepository;
import com.inventoryapp.repositories.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServicesImpl.class);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDTO.class);
    }

//    Creo que tenemmos un problema con el model mapper. Necesitamos revisarlo
    // revisar que el dto tenga la variable observation
    @Override
    public ProductDTO findByName(String name) {
        Product p = productRepository.findByName(name);

        ProductDTO productDTO = ProductDTO.builder()
                .id(p.getId())
                .price(p.getPrice())
                .available(p.getAvailable())
                .code(p.getCode())
                .name(p.getName())
                .stock(p.getStock())
                .costPrice(p.getCostPrice())
                .expDate(p.getExpDate())
                .createdAt(p.getCreatedAt())
                .description(p.getDescription())
                .warehouseId(p.getWarehouse().getId())
                .categoryId(p.getCategory().getId())
                .build();

        return productDTO;
    }

    @Override
    public ProductDTO findByCode(String code) {
        return modelMapper.map(productRepository.findByCode(code), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return productRepository.findByCategory(category).stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
        Warehouse warehouse = warehouseRepository.findById(product.getWarehouseId()).orElse(null);

        Product productEntity = Product.builder()
                .price(product.getPrice())
                .available(product.getAvailable())
                .code(product.getCode())
                .name(product.getName())
                .stock(product.getStock())
                .costPrice(product.getCostPrice())
                .expDate(product.getExpDate())
                .createdAt(new Date())
                .description(product.getDescription())
                .warehouse(warehouse)
                .category(category)
                .build();

        productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO product) {
        Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
        Warehouse warehouse = warehouseRepository.findById(product.getWarehouseId()).orElse(null);
        Product productEntity = productRepository.findById(id).orElse(null);

        productEntity = Product.builder()
                .id(id)
                .price(product.getPrice())
                .costPrice(product.getCostPrice())
                .available(product.getAvailable())
                .code(product.getCode())
                .name(product.getName())
                .stock(product.getStock())
                .createdAt(new Date())
                .expDate(product.getExpDate())
                .description(product.getDescription())
                .warehouse(warehouse)
                .category(category)
                .build();

        productRepository.save(productEntity);

        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);

        return "product deleted";
    }
}
