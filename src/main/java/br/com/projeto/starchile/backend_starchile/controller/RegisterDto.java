package br.com.projeto.starchile.backend_starchile.controller;

import br.com.projeto.starchile.backend_starchile.model.login.Role;

public record RegisterDto(String user_name, String password, Role role) {
}
