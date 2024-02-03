package com.example.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setNome(updatedUser.getNome());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setIdade(updatedUser.getIdade());
            existingUser.setSaldo(updatedUser.getSaldo());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
