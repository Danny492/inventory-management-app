package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.CategoryEntity;
import com.inventoryapp.entities.ProductEntity;

import java.util.List;

public interface ProductServices {

    List<ProductEntity>  findAll();
    ProductEntity findById(Long id);
    ProductEntity findByName(String name);
    ProductEntity findByCode(String code);
    List<ProductEntity> findByCategory(String category);
    ProductEntity save(ProductDTO product);
    ProductEntity update(Long id, ProductEntity product);
    String delete(Long id);

    //visualizacion del estado de cada producto(disponible, bajo stock, agotado)

}
