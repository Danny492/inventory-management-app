package com.inventoryapp.controllers;

import com.inventoryapp.dtos.InventoryDTO;
import com.inventoryapp.entities.InventoryMovement;
import com.inventoryapp.services.InventoryMovementServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryMovementServicesImpl inventoryServices;

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @PostMapping("/entry")
    public ResponseEntity<?> createEntry(@RequestBody InventoryDTO inventory) {
        if(inventory != null){
            InventoryDTO response = inventoryServices.saveEntry(inventory);
            if(response == null){
                return new ResponseEntity<>("EMPTY",HttpStatus.BAD_REQUEST);
            } else{
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/exit")
    public ResponseEntity<?> createExit(@RequestBody InventoryDTO inventory) {
        if(inventory != null){
            InventoryDTO response = inventoryServices.saveExit(inventory);
            if(response == null){
                return new ResponseEntity<>("EMPTY",HttpStatus.BAD_REQUEST);
            } else{
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateEntry/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable Long id, @RequestBody InventoryDTO inventory) {
        if(inventory != null){
            InventoryDTO response = inventoryServices.updateEntry(id,inventory);
            if(response == null){
                return new ResponseEntity<>("EMPTY",HttpStatus.BAD_REQUEST);
            } else{
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateExit/{id}")
    public ResponseEntity<?> updateExit(@PathVariable Long id, @RequestBody InventoryDTO inventory) {
        logger.info("inventario en exit: " + inventory);
        if(inventory != null){
            InventoryDTO response = inventoryServices.updateExit(id,inventory);
            if(response == null){
                return new ResponseEntity<>("EMPTY",HttpStatus.BAD_REQUEST);
            } else{
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        if(inventoryServices.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(inventoryServices.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        InventoryDTO inventoryDTO = inventoryServices.findById(id);
        if(inventoryDTO != null){
            return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String message = inventoryServices.delete(id);
        if(message != null){
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
