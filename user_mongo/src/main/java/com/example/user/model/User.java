package com.example.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {
    @Id
    private String id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotNull
    private Integer idade;
    @NotNull
    private Double saldo;
}