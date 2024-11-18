package br.com.projeto.starchile.backend_starchile.security;

import br.com.projeto.starchile.backend_starchile.model.login.Login;
import br.com.projeto.starchile.backend_starchile.repository.LoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;


@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginRepository loginRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = jwtService.validateToken(token);

        if (login != null){
            Login user = loginRepository.findByUserName(login).orElseThrow(()-> new RuntimeException("User not found"));
            var authorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)return null;
        return authHeader.replace("Bearer","").trim();
    }
}
