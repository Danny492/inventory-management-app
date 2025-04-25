package com.inventoryapp.services;

import com.inventoryapp.dtos.InventoryDTO;
import com.inventoryapp.entities.InventoryMovement;

import java.util.List;

public interface InventoryMovementService {
    List<InventoryDTO> findAll();
    InventoryDTO findById(Long id);
    InventoryDTO saveEntry(InventoryDTO inventoryMovement);
    InventoryDTO saveExit(InventoryDTO inventoryMovement);
    InventoryDTO updateEntry(Long id, InventoryDTO inventoryMovement);
    InventoryDTO updateExit(Long id, InventoryDTO inventoryMovement);
    String delete(Long id);
}
