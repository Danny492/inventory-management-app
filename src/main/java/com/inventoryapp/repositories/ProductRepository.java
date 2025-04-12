package com.inventoryapp.repositories;

import com.inventoryapp.entities.Category;
import com.inventoryapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);
    Product findByName(String name);
    List<Product> findByCategory(Category category);
}
