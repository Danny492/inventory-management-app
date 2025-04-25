package com.inventoryapp.services;

import com.inventoryapp.entities.User;
import com.inventoryapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User oldUser = findById(id);
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());

        return userRepository.save(oldUser);
    }

    @Override
    public String delete(Long id) {
        if(id != null) {
            userRepository.deleteById(id);
            return "user deleted";
        } else {
            return null;
        }
    }
}
