package br.com.projeto.starchile.backend_starchile.service;

import br.com.projeto.starchile.backend_starchile.controller.RegisterDto;
import br.com.projeto.starchile.backend_starchile.model.login.Login;
import br.com.projeto.starchile.backend_starchile.model.login.LoginDto;
import br.com.projeto.starchile.backend_starchile.model.login.ResponseDto;
import br.com.projeto.starchile.backend_starchile.repository.LoginRepository;
import br.com.projeto.starchile.backend_starchile.security.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    //criar a classe de serviço de login

    public ResponseDto login ( LoginDto login){
        var user = repository.findByUserName(login.username()).orElseThrow(()->new RuntimeException("Usuario não encontrado"));
        if (passwordEncoder.matches(login.password(), user.getPassword())){
            String token = this.jwtService.generateToken(user);

            return new ResponseDto(user.getUserName(), token);
        }
        throw  new RuntimeException("Erro ao fazer o login, verificar a senha");
    }

    public ResponseDto registro (RegisterDto register ){
        var user = repository.findByUserName(register.user_name());
        if(user.isEmpty()){
            Login newUser = new Login(register);
            newUser.setPassword(passwordEncoder.encode(register.password()));
            newUser.setRole(register.role());
            repository.save(newUser);
            String token = jwtService.generateToken(newUser);
            return new ResponseDto(newUser.getUserName(), token);
        }

        throw new RuntimeException("Usuario ja existe");
    }

    public RegisterDto buscarRole(String token){
        JwtService service = new JwtService();
        String name = this.buscarUserPorToken(token);
        var dado = repository.findByUserName(name).orElseThrow(()->new RuntimeException("usuario nao encontrado"));
        return new RegisterDto(dado.getUserName(), dado.getPassword(), dado.getRole());
    }

    public void deletarLogin(String userName){
        Login login = repository.findByUserName(userName).orElseThrow();
        repository.delete(login);
    }

    public String buscarUserPorToken(String token){
       return JWT.decode(token).getSubject();

    }
}
