package com.inventoryapp.controllers;

import com.inventoryapp.dtos.WarehouseDTO;
import com.inventoryapp.services.WarehouseServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseServicesImpl warehouseServices;

    @GetMapping
    public ResponseEntity<?> getAllWarehouses() {
        if(warehouseServices.findAll().isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(warehouseServices.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable Long id) {
        WarehouseDTO warehouseDTO = warehouseServices.findById(id);
        if(warehouseDTO != null) {
            return new ResponseEntity<>(warehouseDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createWarehouse(@RequestBody WarehouseDTO warehouse) {
        WarehouseDTO warehouseDTO = warehouseServices.save(warehouse);
        if(warehouseDTO != null) {
            return new ResponseEntity<>(warehouseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO warehouse) {
        WarehouseDTO warehouseDTO = warehouseServices.update(id, warehouse);
        if(warehouseDTO != null) {
            return new ResponseEntity<>(warehouseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        String message = warehouseServices.delete(id);
        if(message != null) {
            return new ResponseEntity<>(message,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
