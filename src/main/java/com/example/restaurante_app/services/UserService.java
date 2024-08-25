package com.example.restaurante_app.services;

import com.example.restaurante_app.dtos.EditUser;
import com.example.restaurante_app.dtos.NewUser;
import com.example.restaurante_app.exceptions.ResourceNotFoundException;
import com.example.restaurante_app.entities.User;
import com.example.restaurante_app.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public User createUser(NewUser newUser) {
        var user = new User();
        BeanUtils.copyProperties(newUser, user);
        // TODO: Implement hashing
        user.setPassword(newUser.password());
        return userRepository.save(user);
    }

    public User updateUser(Long id, EditUser editUser) {
        User user = userRepository.getReferenceById(id);
        BeanUtils.copyProperties(editUser, user);
        // TODO: Implement hashing
        user.setPassword(editUser.password());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
