package com.inventoryapp.services;

import com.inventoryapp.dtos.CategoryDTO;
import com.inventoryapp.entities.User;

import java.util.List;

public interface UserServices {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(Long id, User user);
    String delete(Long id);
}
