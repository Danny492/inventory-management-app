package com.inventoryapp.repositories;

import com.inventoryapp.entities.CategoryEntity;
import com.inventoryapp.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByCode(String code);
    ProductEntity findByName(String name);
    List<ProductEntity> findByCategory(CategoryEntity category);
}
