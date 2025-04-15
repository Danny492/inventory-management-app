package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.Product;

import java.util.List;

public interface ProductServices {

    List<ProductDTO>  findAll();
    ProductDTO findById(Long id);
    Product findByName(String name);
    Product findByCode(String code);
    List<Product> findByCategory(String category);
    ProductDTO save(ProductDTO product);
    ProductDTO update(Long id, ProductDTO product);
    String delete(Long id);

    //visualizacion del estado de cada producto(disponible, bajo stock, agotado)

}
