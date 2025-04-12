package com.inventoryapp.repositories;

import com.inventoryapp.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);
}
