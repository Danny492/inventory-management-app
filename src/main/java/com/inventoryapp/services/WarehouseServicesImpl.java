package com.inventoryapp.services;

import com.inventoryapp.entities.Warehouse;

import java.util.List;

public class WarehouseServicesImpl implements WarehouseServices {
    @Override
    public List<Warehouse> findAll() {
        return List.of();
    }

    @Override
    public Warehouse findById(Long id) {
        return null;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return null;
    }

    @Override
    public Warehouse update(Long id, Warehouse warehouse) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return "";
    }
}
