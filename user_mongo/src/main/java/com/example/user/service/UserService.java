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

    // Create (Criar) - Adiciona um novo usuário
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read (Ler) - Busca um usuário pelo ID
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // Read (Ler) - Busca todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update (Atualizar) - Atualiza as informações de um usuário
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
        return null; // Pode ser mais apropriado lançar uma exceção caso o usuário não seja encontrado
    }

    // Delete (Excluir) - Remove um usuário pelo ID
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
