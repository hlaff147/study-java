package com.studycode.springcode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String senha;
    private String cpf;
    private String email;

    public User(String nome, String senha, String cpf, String email) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
    }
    public User(String nome, String senha, String cpf, Long id, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.id = id;
    }

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
