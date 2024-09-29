package br.com.projeto.starchile.backend_starchile.controller;

import br.com.projeto.starchile.backend_starchile.domain.login.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/star-chile")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return null;
    }
}
