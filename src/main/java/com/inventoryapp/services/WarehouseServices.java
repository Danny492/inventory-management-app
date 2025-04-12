package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.Warehouse;

import java.util.List;

public interface WarehouseServices {

    List<Warehouse> findAll();
    Warehouse findById(Long id);
    Warehouse save(Warehouse warehouse);
    Warehouse update(Long id, Warehouse warehouse);
    String delete(Long id);
}
