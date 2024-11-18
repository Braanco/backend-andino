package br.com.projeto.starchile.backend_starchile.repository;

import br.com.projeto.starchile.backend_starchile.model.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByUserName(String username);
}
