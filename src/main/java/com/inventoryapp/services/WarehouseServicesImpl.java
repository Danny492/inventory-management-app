package com.inventoryapp.services;

import com.inventoryapp.dtos.WarehouseDTO;
import com.inventoryapp.entities.Warehouse;
import com.inventoryapp.repositories.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServicesImpl implements WarehouseServices {

    @Autowired
    private WarehouseRepository warehouseRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<WarehouseDTO> findAll() {
        return warehouseRepository.findAll().stream().map(warehouse -> modelMapper.map(warehouse, WarehouseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public WarehouseDTO findById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);

        return modelMapper.map(warehouse, WarehouseDTO.class);
    }

    @Override
    public WarehouseDTO save(WarehouseDTO warehouse) {
        Warehouse warehouse1 = modelMapper.map(warehouse, Warehouse.class);
        warehouseRepository.save(warehouse1);

        return modelMapper.map(warehouse1, WarehouseDTO.class);
    }

    @Override
    public WarehouseDTO update(Long id, WarehouseDTO warehouse) {
       Warehouse warehouse1 = warehouseRepository.findById(id).orElse(null);
       warehouse.setId(id);
       warehouse1.setName(warehouse.getName());
       warehouse1.setDescription(warehouse.getDescription());
       warehouse1.setLevel(warehouse.getLevel());

       warehouseRepository.save(warehouse1);

       return modelMapper.map(warehouse1, WarehouseDTO.class);
    }

    @Override
    public String delete(Long id) {
        warehouseRepository.deleteById(id);
        return "Warehouse deleted";
    }
}
