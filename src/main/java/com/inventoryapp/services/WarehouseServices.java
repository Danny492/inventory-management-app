package com.inventoryapp.services;

import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.dtos.WarehouseDTO;
import com.inventoryapp.entities.Warehouse;

import java.util.List;

public interface WarehouseServices {

    List<WarehouseDTO> findAll();
    WarehouseDTO findById(Long id);
    WarehouseDTO save(WarehouseDTO warehouse);
    WarehouseDTO update(Long id, WarehouseDTO warehouse);
    String delete(Long id);
}
