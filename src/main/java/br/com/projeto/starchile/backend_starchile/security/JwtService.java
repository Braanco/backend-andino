package br.com.projeto.starchile.backend_starchile.security;

import br.com.projeto.starchile.backend_starchile.model.login.Login;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {

    @Value("${spring.secret.key}")
    private String secret;

    public String generateToken(Login login) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String token = JWT.create()
                    .withIssuer("andino")
                    .withSubject(login.getUserName())
                    .withExpiresAt(expiredeToken())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant expiredeToken() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("andino")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }

    }
}
