package br.com.projeto.starchile.backend_starchile.repository;

import br.com.projeto.starchile.backend_starchile.domain.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
