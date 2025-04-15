package com.inventoryapp.controllers;

import com.inventoryapp.dtos.WarehouseDTO;
import com.inventoryapp.services.WarehouseServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseServicesImpl warehouseServices;

    @GetMapping
    public List<WarehouseDTO> getAllProducts() {
        return warehouseServices.findAll();
    }

    @GetMapping("/{id}")
    public WarehouseDTO getProductById(@PathVariable Long id) {
        return warehouseServices.findById(id);
    }

    @PostMapping
    public WarehouseDTO createProduct(@RequestBody WarehouseDTO warehouse) {
        return warehouseServices.save(warehouse);
    }

    @PutMapping("/{id}")
    public WarehouseDTO updateProduct(@PathVariable Long id, @RequestBody WarehouseDTO warehouse) {
        return warehouseServices.update(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return warehouseServices.delete(id);
    }
}
