package com.studycode.springcode.service;

import com.studycode.springcode.dto.UserDTO;
import com.studycode.springcode.entity.User;
import com.studycode.springcode.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User parseToEntity(final UserDTO dto) {
        return new User(dto.getNome(), dto.getSenha(), dto.getCpf(), dto.getId(), dto.getEmail());
    }

    public UserDTO parseToDTO(final User entitie) {
        return UserDTO.builder()
                .id(entitie.getId())
                .cpf(entitie.getCpf())
                .senha(entitie.getSenha())
                .nome(entitie.getNome())
                .email(entitie.getEmail())
                .cpf(entitie.getCpf())
                .build();
    }
    @Transactional()
    public User save(UserDTO dto) {
        return userRepository.save(parseToEntity(dto));
    }

}
