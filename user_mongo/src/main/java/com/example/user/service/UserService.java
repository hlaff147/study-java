package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
        return getUserById(userId)
                .map(existingUser -> {
                    existingUser.setNome(updatedUser.getNome());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setIdade(updatedUser.getIdade());
                    existingUser.setSaldo(updatedUser.getSaldo());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
    }


    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
