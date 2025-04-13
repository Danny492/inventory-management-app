package com.inventoryapp.controllers;

import com.inventoryapp.entities.Warehouse;
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
    public List<Warehouse> getAllProducts() {
        return warehouseServices.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse getProductById(@PathVariable Long id) {
        return warehouseServices.findById(id);
    }

    @PostMapping
    public Warehouse createProduct(@RequestBody Warehouse warehouse) {
        return warehouseServices.save(warehouse);
    }

    @PutMapping("/{id}")
    public Warehouse updateProduct(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        return warehouseServices.update(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return warehouseServices.delete(id);
    }
}
