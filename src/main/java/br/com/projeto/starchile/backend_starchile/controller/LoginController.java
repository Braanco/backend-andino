package br.com.projeto.starchile.backend_starchile.controller;

import br.com.projeto.starchile.backend_starchile.model.login.Login;
import br.com.projeto.starchile.backend_starchile.model.login.LoginDto;
import br.com.projeto.starchile.backend_starchile.model.login.ResponseDto;
import br.com.projeto.starchile.backend_starchile.repository.LoginRepository;
import br.com.projeto.starchile.backend_starchile.security.JwtService;
import br.com.projeto.starchile.backend_starchile.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/andino")
public class LoginController {

   private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


//    @GetMapping("/login")
//    public ResponseEntity login() {
//        return ResponseEntity.status(HttpStatus.FOUND) // Status 302 para redirecionamento
//                .location(URI.create("http://127.0.0.1:5500/index.html")) // Define a URL de redirecionamento
//                .build();
//    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody LoginDto body){
        var data = loginService.login(body);
        if (data.name() != null){
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto){
        var data = loginService.registro(registerDto);
        if (data.name().equals(registerDto.user_name())){
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().build();
    }


}

