package com.example.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
    @Id
    private String id;
    private String nome;
    private String email;
    private Integer idade;
    private Double saldo;

}