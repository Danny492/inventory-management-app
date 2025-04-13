package com.inventoryapp.services;

import com.inventoryapp.entities.Warehouse;
import com.inventoryapp.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServicesImpl implements WarehouseServices {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse update(Long id, Warehouse warehouse) {
       Warehouse warehouse1 = warehouseRepository.findById(id).orElse(null);
       warehouse.setId(id);
       warehouse1.setName(warehouse.getName());
       warehouse1.setDescription(warehouse.getDescription());
       warehouse1.setLevel(warehouse.getLevel());

       return warehouseRepository.save(warehouse1);
    }

    @Override
    public String delete(Long id) {
        warehouseRepository.deleteById(id);
        return "Warehouse deleted";
    }
}
